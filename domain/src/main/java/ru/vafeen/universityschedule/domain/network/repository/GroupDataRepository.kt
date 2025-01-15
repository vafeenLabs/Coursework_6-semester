package ru.vafeen.universityschedule.domain.network.repository

import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

interface GroupDataRepository {
    /**
     * Получает список всех доступных групп с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список строк с названиями групп.
     */
    suspend fun getGroups(): ResponseResult<List<Group>>
}