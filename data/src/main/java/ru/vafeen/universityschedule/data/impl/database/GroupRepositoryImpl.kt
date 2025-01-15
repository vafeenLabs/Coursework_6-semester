package ru.vafeen.universityschedule.data.impl.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vafeen.universityschedule.data.converters.GroupEntityConverter
import ru.vafeen.universityschedule.data.database.AppDatabase
import ru.vafeen.universityschedule.domain.database.GroupRepository
import ru.vafeen.universityschedule.domain.models.Group

/**
 * Реализация репозитория для работы с группами в базе данных.
 *
 * @property groupEntityConverter Конвертер для преобразования объектов Group между слоями.
 * @property db База данных приложения.
 */
internal class GroupRepositoryImpl(
    private val db: AppDatabase,
    private val groupEntityConverter: GroupEntityConverter,
) : GroupRepository {

    private val groupDao = db.groupDao()

    /**
     * Получение списка групп в виде Flow.
     *
     * @return Flow, содержащий список групп.
     */
    override fun getAsFlowGroups(): Flow<List<Group>> =
        groupDao.getAllAsFlow().map { groupEntities ->
            groupEntityConverter.convertABList(groupEntities)
        }

    /**
     * Вставка списка групп в базу данных.
     *
     * @param groups Список групп для вставки.
     */
    override suspend fun insertGroups(groups: List<Group>) =
        groupDao.insert(groups.map { groupEntityConverter.convertBA(it) })

    /**
     * Удаление списка групп из базы данных.
     *
     * @param groups Список групп для удаления.
     */
    override suspend fun deleteGroups(groups: List<Group>) =
        groupDao.delete(groups.map { groupEntityConverter.convertBA(it) })
}
