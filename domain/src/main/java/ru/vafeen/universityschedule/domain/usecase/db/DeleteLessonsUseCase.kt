package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.LessonLocalRepository
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для удаления пар из базы данных.
 *
 * Этот класс отвечает за удаление одной или нескольких пар из репозитория.
 *
 * @property lessonLocalRepository Репозиторий, используемый для взаимодействия с данными уроков.
 */
class DeleteLessonsUseCase(private val lessonLocalRepository: LessonLocalRepository) : UseCase {

    /**
     * Удаляет указанные пары из базы данных.
     *
     * @param lesson Пары, которые нужно удалить. Можно передавать несколько уроков в виде vararg.
     */
    suspend fun invoke(vararg lesson: Lesson) =
        lessonLocalRepository.deleteLessons(lesson.toList())
}
