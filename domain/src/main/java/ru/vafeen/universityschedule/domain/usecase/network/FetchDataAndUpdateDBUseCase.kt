package ru.vafeen.universityschedule.domain.usecase.network

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import ru.vafeen.universityschedule.domain.GSheetsServiceRequestStatus
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertGroupsUseCase

class FetchDataAndUpdateDBUseCase(
    private val getLessonDataUseCase: GetLessonDataUseCase,
    private val getGroupDataUseCase: GetGroupDataUseCase,
    private val cleverUpdatingLessonsUseCase: CleverUpdatingLessonsUseCase,
    private val getAsFlowGroupsUseCase: GetAsFlowGroupsUseCase,
    private val deleteGroupsUseCase: DeleteGroupsUseCase,
    private val insertGroupsUseCase: InsertGroupsUseCase,
) {

    /**
     * Получает данные  и обновляет базу данных.
     *
     * @param link Ссылка откуда нужно получить данные.
     * @param updateRequestStatus Функция обратного вызова для обновления статуса запроса (по умолчанию null).
     */
    suspend fun invoke(
        coroutineScope: CoroutineScope,
        updateRequestStatus: (suspend (GSheetsServiceRequestStatus) -> Unit)? = null
    ) {
        updateRequestStatus?.invoke(GSheetsServiceRequestStatus.Waiting) // Устанавливаем статус ожидания
        val lessonDataFetchingIsSuccessful = coroutineScope.async {
            getLessonDataUseCase.invoke().let {
                when (it) {
                    is ResponseResult.Error -> false
                    is ResponseResult.Success -> {
                        Log.d("lessons", "data = ${it.data}")
                        cleverUpdatingLessonsUseCase.invoke(it.data)
                        true
                    }
                }
            }
        }
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

        when (listOf(
            lessonDataFetchingIsSuccessful.await(),
            groupDataFetchingIsSuccessful.await()
        ).all { successful -> successful }) {
            false -> {
                updateRequestStatus?.invoke(GSheetsServiceRequestStatus.NetworkError) // Обработка ошибки сети
            }

            true -> {
                updateRequestStatus?.invoke(GSheetsServiceRequestStatus.Success) // Устанавливаем статус успеха
            }
        }

    }
}