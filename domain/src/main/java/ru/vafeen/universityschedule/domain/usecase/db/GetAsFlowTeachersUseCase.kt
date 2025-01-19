package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.domain.database.TeacherLocalRepository
import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения списка учителей в виде потока.
 *
 * Этот класс предоставляет возможность получать текущий список учителей из репозитория в виде
 * потока, что позволяет наблюдать за изменениями в данных.
 *
 * @property teacherLocalRepository Репозиторий, используемый для взаимодействия с данными учителей.
 */
class GetAsFlowTeachersUseCase(private val teacherLocalRepository: TeacherLocalRepository) : UseCase {

    /**
     * Возвращает поток, содержащий текущий список учителей.
     *
     * @return [Flow] с итерацией учителей [Teacher].
     */
    fun invoke(): Flow<List<Teacher>> = teacherLocalRepository.getAsFlowTeachers()
}
