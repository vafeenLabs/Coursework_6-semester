package ru.vafeen.universityschedule.domain.database

import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.domain.models.Teacher

/**
 * Интерфейс репозитория для работы с преподавателями.
 */
interface TeacherLocalRepository {

    /**
     * Получает список преподавателей как поток данных.
     *
     * @return [Flow] списка объектов [Teacher].
     */
    fun getAsFlowTeachers(): Flow<List<Teacher>>

    /**
     * Вставляет список преподавателей в базу данных.
     *
     * @param teachers Список объектов [Teacher], которые нужно вставить.
     */
    suspend fun insertTeachers(teachers: List<Teacher>)

    /**
     * Удаляет список преподавателей из базы данных.
     *
     * @param teachers Список объектов [Teacher], которые нужно удалить.
     */
    suspend fun deleteTeachers(teachers: List<Teacher>)
}
