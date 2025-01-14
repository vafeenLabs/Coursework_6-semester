package ru.vafeen.universityschedule.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.data.database.dao.parent.DataAccessObject
import ru.vafeen.universityschedule.data.database.dao.parent.implementation.FlowGetAllImplementation
import ru.vafeen.universityschedule.data.database.entity.GroupEntity

/**
 * Интерфейс DAO для @Entity [Группа][ru.vafeen.universityschedule.data.database.entity.GroupEntity]
 */
@Dao
internal interface GroupDao : DataAccessObject<GroupEntity>, FlowGetAllImplementation<GroupEntity> {

    /**
     * Получение всех групп в виде Flow
     * @return [Flow] со списком всех групп [GroupEntity]
     */
    @Query("select * from `group`")
    override fun getAllAsFlow(): Flow<List<GroupEntity>>
}
