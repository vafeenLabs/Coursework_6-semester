package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.domain.database.LessonLocalRepository
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения списка пар в виде потока.
 *
 * Этот класс предоставляет возможность получать текущий список пар из репозитория в виде
 * потока, что позволяет наблюдать за изменениями в данных.
 *
 * @property lessonLocalRepository Репозиторий, используемый для взаимодействия с данными пар.
 */
class GetAsFlowLessonsUseCase(private val lessonLocalRepository: LessonLocalRepository) : UseCase {

    /**
     * Возвращает поток, содержащий текущий список пар.
     *
     * @return [Flow] с итерацией пар [Lesson].
     */
    fun invoke(): Flow<Iterable<Lesson>> = lessonLocalRepository.getAsFlowLessons()
}
