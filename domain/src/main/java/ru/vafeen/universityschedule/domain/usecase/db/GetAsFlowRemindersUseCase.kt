package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.domain.database.ReminderLocalRepository
import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения списка напоминаний в виде потока.
 *
 * Этот класс предоставляет возможность получать текущий список напоминаний из репозитория в виде
 * потока, что позволяет наблюдать за изменениями в данных.
 *
 * @property reminderLocalRepository Репозиторий, используемый для взаимодействия с данными напоминаний.
 */
class GetAsFlowRemindersUseCase(private val reminderLocalRepository: ReminderLocalRepository) : UseCase {

    /**
     * Возвращает поток, содержащий текущий список напоминаний.
     *
     * @return [Flow] с итерацией напоминаний [Reminder].
     */
    fun invoke(): Flow<Iterable<Reminder>> = reminderLocalRepository.getAsFlowReminders()
}
