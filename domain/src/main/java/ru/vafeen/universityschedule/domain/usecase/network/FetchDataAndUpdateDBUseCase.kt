package ru.vafeen.universityschedule.domain.usecase.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import ru.vafeen.universityschedule.domain.GSheetsServiceRequestStatus
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.usecase.base.UseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingTeachersUseCase

/**
 * Юзкейс для получения данных с сервера и обновления базы данных.
 * Этот класс использует другие юзкейсы для получения и обработки данных о занятиях, группах и преподавателях.
 *
 * @property getLessonDataUseCase Юзкейс для получения данных о занятиях.
 * @property getGroupDataUseCase Юзкейс для получения данных о группах.
 * @property getTeacherDataUseCase Юзкейс для получения данных о преподавателях.
 * @property cleverUpdatingLessonsUseCase Юзкейс для обновления занятий в базе данных.
 * @property cleverUpdatingGroupsUseCase Юзкейс для обновления групп в базе данных.
 * @property cleverUpdatingTeachersUseCase Юзкейс для обновления преподавателей в базе данных.
 */
class FetchDataAndUpdateDBUseCase(
    private val getLessonDataUseCase: GetLessonDataUseCase,
    private val getGroupDataUseCase: GetGroupDataUseCase,
    private val getTeacherDataUseCase: GetTeacherDataUseCase,
    private val cleverUpdatingLessonsUseCase: CleverUpdatingLessonsUseCase,
    private val cleverUpdatingGroupsUseCase: CleverUpdatingGroupsUseCase,
    private val cleverUpdatingTeachersUseCase: CleverUpdatingTeachersUseCase,
) : UseCase {

    /**
     * Получает данные с сервера и обновляет базу данных.
     *
     * Этот метод выполняет асинхронные операции по получению данных о занятиях, группах и преподавателях.
     * В зависимости от успешности этих операций обновляются соответствующие данные в базе данных.
     *
     * @param coroutineScope Область корутины для выполнения асинхронных операций.
     * @param updateRequestStatus Функция обратного вызова для обновления статуса запроса (по умолчанию null).
     * Статусы могут включать:
     * - [GSheetsServiceRequestStatus.Waiting] — статус ожидания начала запроса.
     * - [GSheetsServiceRequestStatus.NetworkError] — статус ошибки сети, если одна из операций завершилась неудачно.
     * - [GSheetsServiceRequestStatus.Success] — статус успешного завершения всех операций.
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
                    is ResponseResult.Error -> false // Ошибка при получении данных о занятиях
                    is ResponseResult.Success -> {
                        cleverUpdatingLessonsUseCase.invoke(it.data) // Обновляем занятия в базе данных
                        true // Успех
                    }
                }
            }
        }

        // Асинхронное получение данных о группах
        val groupDataFetchingIsSuccessful = coroutineScope.async {
            getGroupDataUseCase.invoke().let {
                when (it) {
                    is ResponseResult.Error -> false // Ошибка при получении данных о группах
                    is ResponseResult.Success -> {
                        cleverUpdatingGroupsUseCase.invoke(it.data) // Обновляем группы в базе данных
                        true // Успех
                    }
                }
            }
        }

        // Асинхронное получение данных о преподавателях
        val teacherDataFetchingIsSuccessful = coroutineScope.async {
            getTeacherDataUseCase.invoke().let {
                when (it) {
                    is ResponseResult.Error -> false // Ошибка при получении данных о преподавателях
                    is ResponseResult.Success -> {
                        cleverUpdatingTeachersUseCase.invoke(it.data) // Обновляем преподавателей в базе данных
                        true // Успех
                    }
                }
            }
        }

        // Ожидание завершения асинхронных операций и обработка результата
        when (listOf(
            lessonDataFetchingIsSuccessful.await(),
            groupDataFetchingIsSuccessful.await(),
            teacherDataFetchingIsSuccessful.await()
        ).all { successful -> successful }) {
            false -> {
                // Обработка ошибки сети, если хотя бы одна операция завершилась неудачно
                updateRequestStatus?.invoke(GSheetsServiceRequestStatus.NetworkError)
            }

            true -> {
                // Устанавливаем статус успеха, если все операции завершились успешно
                updateRequestStatus?.invoke(GSheetsServiceRequestStatus.Success)
            }
        }
    }
}