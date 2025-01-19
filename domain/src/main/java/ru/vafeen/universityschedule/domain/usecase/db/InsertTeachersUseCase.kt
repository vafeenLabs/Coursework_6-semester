package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.TeacherLocalRepository
import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для добавления учителей в базу данных.
 *
 * Этот класс отвечает за вставку одного или нескольких учителей в репозиторий.
 *
 * @property teacherLocalRepository Репозиторий, используемый для взаимодействия с данными учителей.
 */
class InsertTeachersUseCase(private val teacherLocalRepository: TeacherLocalRepository) : UseCase {

    /**
     * Вставляет указанных учителей в базу данных.
     *
     * @param teachers Учителя, которых нужно добавить. Можно передавать несколько учителей в виде vararg.
     */
    suspend fun invoke(vararg teachers: Teacher) =
        teacherLocalRepository.insertTeachers(teachers.toList())
}
