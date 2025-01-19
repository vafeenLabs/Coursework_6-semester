package ru.vafeen.universityschedule.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.data.database.dao.parent.DataAccessObject
import ru.vafeen.universityschedule.data.database.dao.parent.implementation.FlowGetAllImplementation
import ru.vafeen.universityschedule.data.database.entity.TeacherEntity

@Dao
internal interface TeacherDao : DataAccessObject<TeacherEntity>, FlowGetAllImplementation<TeacherEntity> {
    @Query("select * from Teacher")
    override fun getAllAsFlow(): Flow<List<TeacherEntity>>
}