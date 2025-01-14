package ru.vafeen.universityschedule.data.impl.network.repository

import ru.vafeen.universityschedule.data.converters.LessonDTOConverter
import ru.vafeen.universityschedule.data.network.service.LessonDataService
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.repository.LessonDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import java.io.IOException
import java.net.UnknownHostException

internal class LessonDataRepositoryImpl(
    private val lessonDTOConverter: LessonDTOConverter,
    private val lessonDataService: LessonDataService,
) : LessonDataRepository {
    private suspend fun <T> getResponseWrappedAllErrors(response: suspend () -> ResponseResult<T>): ResponseResult<T> =
        try {
            response()
        } catch (e: UnknownHostException) {
            // Обработка ошибки отсутствия подключения к интернету
            ResponseResult.Error(UnknownHostException("Нет подключения к интернету"))
        } catch (e: IOException) {
            // Обработка сетевой ошибки
            ResponseResult.Error(IOException("Ошибка сети: ${e.localizedMessage}"))
        } catch (e: Exception) {
            // Обработка других исключений
            ResponseResult.Error(Exception("Неизвестная ошибка: ${e.localizedMessage}"))
        }

    /**
     * Получает список занятий для указанной группы с сервера.
     *
     * @param group Идентификатор группы, для которой требуется получить расписание.
     * @return [ResponseResult] с результатом запроса, содержащим список занятий ([Lesson]) или информацию об ошибке.
     */
    override suspend fun getLessonDataByGroup(group: String): ResponseResult<List<Lesson>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(
                lessonDTOConverter.convertList(
                    lessonDataService.getLessonDataByGroup(
                        group
                    )
                )
            )
        }

    override suspend fun getAllLessonData(): ResponseResult<List<Lesson>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(lessonDTOConverter.convertList(lessonDataService.getLessonData()))
        }

    /**
     * Получает список доступных групп с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список групп ([String]) или информацию об ошибке.
     */
    override suspend fun getGroups(): ResponseResult<List<String>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(lessonDataService.getGroups())
        }
}