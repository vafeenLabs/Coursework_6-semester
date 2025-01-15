package ru.vafeen.universityschedule.domain.network.repository

import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

/**
 * Интерфейс репозитория для работы с данными занятий, полученными с сервера.
 * Предоставляет методы для получения расписания занятий.
 */
interface LessonRemoteRepository {

    /**
     * Получает список занятий для указанной группы с сервера.
     *
     * @param groupId Идентификатор группы, для которой необходимо получить расписание.
     * @return [ResponseResult] с результатом запроса, содержащим список объектов [Lesson] или информацию об ошибке.
     */
    suspend fun getLessonDataByGroupId(groupId: Int): ResponseResult<List<Lesson>>

    /**
     * Получает полный список всех занятий с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список объектов [Lesson] или информацию об ошибке.
     */
    suspend fun getAllLessonData(): ResponseResult<List<Lesson>>
}