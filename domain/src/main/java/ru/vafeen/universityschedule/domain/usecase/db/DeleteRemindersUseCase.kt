package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.ReminderLocalRepository
import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для удаления напоминаний из базы данных.
 *
 * Этот класс отвечает за удаление одного или нескольких напоминаний из репозитория.
 *
 * @property reminderLocalRepository Репозиторий, используемый для взаимодействия с данными напоминаний.
 */
class DeleteRemindersUseCase(private val reminderLocalRepository: ReminderLocalRepository) : UseCase {

    /**
     * Удаляет указанные напоминания из базы данных.
     *
     * @param reminder Напоминания, которые нужно удалить. Можно передавать несколько напоминаний в виде vararg.
     */
    suspend fun invoke(vararg reminder: Reminder) =
        reminderLocalRepository.deleteReminders(reminder.toList())
}
