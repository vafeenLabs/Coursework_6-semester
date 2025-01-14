package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.GSheetsServiceRequestStatus
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.usecase.base.UseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingLessonsUseCase

/**
 * UseCase для получения данных и обновления базы данных.
 *
 * Этот класс отвечает за выполнение операции получения данных
 * и обновления базы данных на основе полученных данных.
 *
 * @property getLessonDataUseCase UseCase для получения данных
 * @property cleverUpdatingLessonsUseCase UseCase для интеллектуального обновления пар.
 */
class GetLessonDataAndUpdateDBUseCase(
    private val getLessonDataUseCase: GetLessonDataUseCase,
    private val cleverUpdatingLessonsUseCase: CleverUpdatingLessonsUseCase,
) : UseCase {

    /**
     * Получает данные  и обновляет базу данных.
     *
     * @param link Ссылка откуда нужно получить данные.
     * @param updateRequestStatus Функция обратного вызова для обновления статуса запроса (по умолчанию null).
     */
    suspend fun invoke(
        updateRequestStatus: (suspend (GSheetsServiceRequestStatus) -> Unit)? = null
    ) {
        updateRequestStatus?.invoke(GSheetsServiceRequestStatus.Waiting) // Устанавливаем статус ожидания
        getLessonDataUseCase.invoke().also {
            when (it) {
                is ResponseResult.Error -> {
                    updateRequestStatus?.invoke(GSheetsServiceRequestStatus.NetworkError) // Обработка ошибки сети
                }

                is ResponseResult.Success -> {
                    cleverUpdatingLessonsUseCase.invoke(it.data) // Обновление базы данных с полученными данными
                    updateRequestStatus?.invoke(GSheetsServiceRequestStatus.Success) // Устанавливаем статус успеха
                }
            }
        }
    }
}
