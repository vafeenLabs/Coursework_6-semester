package ru.vafeen.universityschedule.domain.usecase.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import ru.vafeen.universityschedule.domain.GSheetsServiceRequestStatus
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.usecase.base.UseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertGroupsUseCase

/**
 * Юзкейс для получения данных с сервера и обновления базы данных.
 * Использует другие юзкейсы для получения и обработки данных.
 *
 * @property getLessonDataUseCase Юзкейс для получения данных о занятиях.
 * @property getGroupDataUseCase Юзкейс для получения данных о группах.
 * @property cleverUpdatingLessonsUseCase Юзкейс для обновления занятий в базе данных.
 * @property getAsFlowGroupsUseCase Юзкейс для получения групп в виде потока.
 * @property deleteGroupsUseCase Юзкейс для удаления групп из базы данных.
 * @property insertGroupsUseCase Юзкейс для вставки групп в базу данных.
 */
class FetchDataAndUpdateDBUseCase(
    private val getLessonDataUseCase: GetLessonDataUseCase,
    private val getGroupDataUseCase: GetGroupDataUseCase,
    private val cleverUpdatingLessonsUseCase: CleverUpdatingLessonsUseCase,
    private val getAsFlowGroupsUseCase: GetAsFlowGroupsUseCase,
    private val deleteGroupsUseCase: DeleteGroupsUseCase,
    private val insertGroupsUseCase: InsertGroupsUseCase,
) : UseCase {

    /**
     * Получает данные с сервера и обновляет базу данных.
     *
     * @param coroutineScope Область корутины для выполнения асинхронных операций.
     * @param updateRequestStatus Функция обратного вызова для обновления статуса запроса (по умолчанию null).
     */
    suspend fun invoke(
        coroutineScope: CoroutineScope,
        updateRequestStatus: (suspend (GSheetsServiceRequestStatus) -> Unit)? = null
    ) {
        // Устанавливаем статус ожидания
        updateRequestStatus?.invoke(GSheetsServiceRequestStatus.Waiting)

        // Асинхронное получение данных о занятиях
        val lessonDataFetchingIsSuccessful = coroutineScope.async {
            getLessonDataUseCase.invoke().let {
                when (it) {
                    is ResponseResult.Error -> false
                    is ResponseResult.Success -> {
                        cleverUpdatingLessonsUseCase.invoke(it.data)
                        true
                    }
                }
            }
        }

        // Асинхронное получение данных о группах
        val groupDataFetchingIsSuccessful = coroutineScope.async {
            getGroupDataUseCase.invoke().let {
                when (it) {
                    is ResponseResult.Error -> false
                    is ResponseResult.Success -> {
                        val groups = getAsFlowGroupsUseCase.invoke().first()
                        deleteGroupsUseCase.invoke(groups)
                        insertGroupsUseCase.invoke(it.data)
                        true
                    }
                }
            }
        }

        // Ожидание завершения асинхронных операций
        when (listOf(
            lessonDataFetchingIsSuccessful.await(),
            groupDataFetchingIsSuccessful.await()
        ).all { successful -> successful }) {
            false -> {
                // Обработка ошибки сети
                updateRequestStatus?.invoke(GSheetsServiceRequestStatus.NetworkError)
            }

            true -> {
                // Устанавливаем статус успеха
                updateRequestStatus?.invoke(GSheetsServiceRequestStatus.Success)
            }
        }
    }
}
