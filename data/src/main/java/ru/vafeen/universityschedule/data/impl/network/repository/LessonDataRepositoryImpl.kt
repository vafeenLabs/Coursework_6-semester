package ru.vafeen.universityschedule.data.impl.network.repository

import ru.vafeen.universityschedule.data.converters.LessonDTOConverter
import ru.vafeen.universityschedule.data.network.service.server.LessonDataService
import ru.vafeen.universityschedule.data.utils.getResponseWrappedAllErrors
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.repository.LessonDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

/**
 * Реализация репозитория для работы с данными занятий через сеть.
 * Использует сервис [LessonDataService] для запросов к серверу и конвертер [LessonDTOConverter] для преобразования данных.
 *
 * @property lessonDTOConverter Конвертер для преобразования данных из формата DTO в модель приложения.
 * @property lessonDataService Сервис для запросов к серверу.
 */
internal class LessonDataRepositoryImpl(
    private val lessonDTOConverter: LessonDTOConverter,
    private val lessonDataService: LessonDataService,
) : LessonDataRepository {

    /**
     * Получает список занятий для указанной группы с сервера.
     *
     * @param groupId Идентификатор группы, для которой требуется получить расписание.
     * @return [ResponseResult] с результатом запроса, содержащим список занятий ([Lesson]) или информацию об ошибке.
     */
    override suspend fun getLessonDataByGroupId(groupId: Int): ResponseResult<List<Lesson>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(
                lessonDTOConverter.convertList(
                    lessonDataService.getLessonDataByGroupId(
                        groupId
                    )
                )
            )
        }

    /**
     * Получает полный список всех занятий с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список занятий ([Lesson]) или информацию об ошибке.
     */
    override suspend fun getAllLessonData(): ResponseResult<List<Lesson>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(lessonDTOConverter.convertList(lessonDataService.getLessonData()))
        }
}
