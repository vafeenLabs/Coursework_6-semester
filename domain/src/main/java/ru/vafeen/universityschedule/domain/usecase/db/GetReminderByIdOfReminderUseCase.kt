package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.ReminderLocalRepository
import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения напоминания по его идентификатору.
 *
 * Этот класс предоставляет возможность получать конкретное напоминание из репозитория
 * по его идентификатору.
 *
 * @property reminderLocalRepository Репозиторий, используемый для взаимодействия с данными напоминаний.
 */
class GetReminderByIdOfReminderUseCase(private val reminderLocalRepository: ReminderLocalRepository) :
    UseCase {

    /**
     * Возвращает напоминание по указанному идентификатору.
     *
     * @param idOfReminder Идентификатор напоминания, которое нужно получить.
     * @return Напоминание [Reminder] с указанным идентификатором или null, если напоминание не найдено.
     */
    suspend operator fun invoke(idOfReminder: Int): Reminder? =
        reminderLocalRepository.getReminderByIdOfReminder(idOfReminder = idOfReminder)
}
