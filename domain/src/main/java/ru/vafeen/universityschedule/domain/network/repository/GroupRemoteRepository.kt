package ru.vafeen.universityschedule.domain.network.repository

import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

/**
 * Интерфейс репозитория для работы с данными групп через сеть.
 * Предоставляет методы для получения информации о группах.
 */
interface GroupRemoteRepository {

    /**
     * Получает список всех доступных групп с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список объектов [Group] или информацию об ошибке.
     */
    suspend fun getGroups(): ResponseResult<List<Group>>
}
