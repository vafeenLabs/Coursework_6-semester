package ru.vafeen.universityschedule.data.impl.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vafeen.universityschedule.data.converters.TeacherConverter
import ru.vafeen.universityschedule.data.database.AppDatabase
import ru.vafeen.universityschedule.domain.database.TeacherLocalRepository
import ru.vafeen.universityschedule.domain.models.Teacher

internal class TeacherLocalRepositoryImpl(
    private val teacherConverter: TeacherConverter,
    private val db: AppDatabase
) : TeacherLocalRepository {

    private val teacherDao = db.teacherDao()

    /**
     * Получение списка преподавателей в виде Flow.
     *
     * @return Flow, содержащий список преподавателей.
     */
    override fun getAsFlowTeachers(): Flow<List<Teacher>> =
        teacherDao.getAllAsFlow().map {
            teacherConverter.convertABList(it)
        }

    /**
     * Вставка списка преподавателей в базу данных.
     *
     * @param teachers Список преподавателей для вставки.
     */
    override suspend fun insertTeachers(teachers: List<Teacher>) =
        teacherDao.insert(teacherConverter.convertBAList(teachers))

    /**
     * Удаление списка преподавателей из базы данных.
     *
     * @param teachers Список преподавателей для удаления.
     */
    override suspend fun deleteTeachers(teachers: List<Teacher>) =
        teacherDao.delete(teacherConverter.convertBAList(teachers))
}
