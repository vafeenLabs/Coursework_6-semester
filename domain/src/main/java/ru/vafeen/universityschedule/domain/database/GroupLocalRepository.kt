package ru.vafeen.universityschedule.domain.database

import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.domain.models.Group

/**
 * Интерфейс репозитория для работы с группами.
 */
interface GroupLocalRepository {

    /**
     * Получает список групп как поток данных.
     *
     * @return [Flow] списка объектов [Group].
     */
    fun getAsFlowGroups(): Flow<List<Group>>

    /**
     * Вставляет список групп в базу данных.
     *
     * @param groups Список объектов [Group], которые нужно вставить.
     */
    suspend fun insertGroups(groups: List<Group>)

    /**
     * Удаляет список групп из базы данных.
     *
     * @param groups Список объектов [Group], которые нужно удалить.
     */
    suspend fun deleteGroups(groups: List<Group>)
}
