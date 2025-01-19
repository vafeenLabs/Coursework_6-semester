package ru.vafeen.universityschedule.domain.usecase.db

import android.util.Log
import kotlinx.coroutines.flow.first
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.usecase.base.UseCase
import ru.vafeen.universityschedule.domain.utils.containsLesson

/**
 * UseCase для интеллектуального обновления пар.
 *
 * Этот класс отвечает за:
 * 1. Добавление новых пар в базу данных.
 * 2. Удаление старых пар, которые отсутствуют в новых данных.
 * 3. Удаление напоминаний, связанных с удаленными парами.
 *
 * @property getAsFlowLessonsUseCase UseCase для получения текущего списка пар в виде Flow.
 * @property insertLessonsUseCase UseCase для добавления новых пар в базу данных.
 * @property deleteLessonsUseCase UseCase для удаления пар из базы данных.
 * @property deleteUseLessRemindersForLessonsUseCase UseCase для удаления напоминаний, связанных с удаленными парами.
 */
class CleverUpdatingLessonsUseCase(
    private val getAsFlowLessonsUseCase: GetAsFlowLessonsUseCase,
    private val insertLessonsUseCase: InsertLessonsUseCase,
    private val deleteLessonsUseCase: DeleteLessonsUseCase,
    private val deleteUseLessRemindersForLessonsUseCase: DeleteUseLessRemindersForLessonsUseCase,
) : UseCase {

    /**
     * Выполняет обновление пар.
     *
     * Сравнивает текущий список пар с новыми данными и выполняет следующие действия:
     * 1. Добавляет новые пары, которых еще нет в базе данных.
     * 2. Удаляет пары, отсутствующие в новых данных.
     * 3. Удаляет напоминания, связанные с удаленными парами.
     *
     * @param newLessons Список новых пар, которые должны быть в базе данных.
     */
    suspend fun invoke(newLessons: List<Lesson>) {
        // Получаем текущий список пар из базы данных.
        val lastLessons = getAsFlowLessonsUseCase.invoke().first()

        // Списки для добавления новых или обновленных пар и удаления старых пар.
        val result = mutableListOf<Lesson>()
        val resultForDelete = mutableListOf<Lesson>()

        // Обработка новых пар и формирование списка для добавления или обновления.
        for (newLesson in newLessons) {
            result.add(lastLessons.containsLesson(lesson = newLesson) ?: newLesson)
        }

        // Определение старых пар, которые необходимо удалить.
        for (lastLesson in lastLessons) {
            if (newLessons.containsLesson(lesson = lastLesson) == null) {
                resultForDelete.add(lastLesson)
            }
        }

        // Логируем результаты для отладки (опционально).
        Log.d("CleverUpdating", "New or updated lessons: $result")
        Log.d("CleverUpdating", "Lessons to delete: $resultForDelete")

        // Добавляем новые или обновленные пары в базу данных.
        insertLessonsUseCase.invoke(*result.toTypedArray())

        // Удаляем старые пары из базы данных.
        deleteLessonsUseCase.invoke(*resultForDelete.toTypedArray())

        // Удаляем напоминания, связанные с удаленными парами.
        deleteUseLessRemindersForLessonsUseCase.invoke(resultForDelete)
    }
}
