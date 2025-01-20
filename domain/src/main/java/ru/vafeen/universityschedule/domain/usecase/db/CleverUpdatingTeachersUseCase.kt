package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.first
import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

class CleverUpdatingTeachersUseCase(
    private val deleteTeachersUseCase: DeleteTeachersUseCase,
    private val insertTeachersUseCase: InsertTeachersUseCase,
    private val getAsFlowTeachersUseCase: GetAsFlowTeachersUseCase,
) : UseCase {

    suspend operator fun invoke(newTeachers: List<Teacher>) {
        // Получаем текущий список учителей из базы данных.
        val lastTeachers = getAsFlowTeachersUseCase.invoke().first()

        // Списки для добавления новых и удаления старых учителей
        val resultForInsert = mutableListOf<Teacher>()
        val resultForDelete = mutableListOf<Teacher>()

        // Проходим по новым учителям и добавляем их, если они отсутствуют в базе данных.
        for (newTeacher in newTeachers) {
            if (!lastTeachers.contains(newTeacher)) {
                resultForInsert.add(newTeacher)
            }
        }

        // Проходим по текущим учителям и удаляем тех, которых нет в новых данных.
        for (lastTeacher in lastTeachers) {
            if (!newTeachers.contains(lastTeacher)) {
                resultForDelete.add(lastTeacher)
            }
        }

        // Добавляем новых учителей в базу данных.
        insertTeachersUseCase.invoke(*resultForInsert.toTypedArray())

        // Удаляем старых учителей из базы данных.
        deleteTeachersUseCase.invoke(resultForDelete)
    }
}
