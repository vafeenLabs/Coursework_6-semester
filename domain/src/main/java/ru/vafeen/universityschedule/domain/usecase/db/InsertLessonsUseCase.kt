package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.LessonLocalRepository
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для добавления пар в базу данных.
 *
 * Этот класс отвечает за вставку одного или нескольких уроков в репозиторий.
 *
 * @property lessonLocalRepository Репозиторий, используемый для взаимодействия с данными пар.
 */
class InsertLessonsUseCase(private val lessonLocalRepository: LessonLocalRepository) : UseCase {

    /**
     * Вставляет указанные пары в базу данных.
     *
     * @param lesson Пары, которые нужно добавить. Можно передавать несколько пар в виде vararg.
     */
    suspend fun invoke(vararg lesson: Lesson) = lessonLocalRepository.insertLessons(lesson.toList())
}
