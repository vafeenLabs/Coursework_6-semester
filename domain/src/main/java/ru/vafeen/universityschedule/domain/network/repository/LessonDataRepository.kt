package ru.vafeen.universityschedule.domain.network.repository

import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

/**
 * Интерфейс репозитория для работы с данными, полученными с сервера.
 */
interface LessonDataRepository {

    /**
     * Получает список уроков для указанной группы с сервера.
     *
     * @param group Название группы, для которой необходимо получить данные о занятиях.
     * @return [ResponseResult] с результатом запроса, содержащим список объектов [Lesson].
     */
    suspend fun getLessonDataByGroup(group: String): ResponseResult<List<Lesson>>

    suspend fun getAllLessonData(): ResponseResult<List<Lesson>>

    /**
     * Получает список всех доступных групп с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список строк с названиями групп.
     */
    suspend fun getGroups(): ResponseResult<List<String>>
}