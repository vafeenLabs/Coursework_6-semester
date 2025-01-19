package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.TeacherLocalRepository
import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для удаления учителей из базы данных.
 *
 * Этот класс отвечает за удаление одного или нескольких учителей из репозитория.
 *
 * @property teacherLocalRepository Репозиторий, используемый для взаимодействия с данными учителей.
 */
class DeleteTeachersUseCase(private val teacherLocalRepository: TeacherLocalRepository) : UseCase {

    /**
     * Удаляет указанных учителей из базы данных.
     *
     * @param teachers Учителя, которых нужно удалить. Можно передавать несколько учителей в виде списка.
     */
    suspend fun invoke(teachers: List<Teacher>) =
        teacherLocalRepository.deleteTeachers(teachers)
}
