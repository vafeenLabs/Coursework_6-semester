package ru.vafeen.universityschedule.data.impl.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import ru.vafeen.universityschedule.domain.database.ReminderLocalRepository
import ru.vafeen.universityschedule.domain.models.model_additions.ReminderType
import ru.vafeen.universityschedule.domain.scheduler.SchedulerExtra
import ru.vafeen.universityschedule.domain.usecase.notification.NotificationAbout15MinutesBeforeLessonUseCase
import ru.vafeen.universityschedule.domain.usecase.notification.NotificationAfterBeginningLessonForBeCheckedAtThisLesson

class ReminderReceiver : BroadcastReceiver(), KoinComponent {

    private val reminderRepository: ReminderLocalRepository = getKoin().get()
    private val notificationAfterBeginningLessonForBeCheckedAtThisLesson: NotificationAfterBeginningLessonForBeCheckedAtThisLesson =
        getKoin().get()
    private val notificationAbout15MinutesBeforeLessonUseCase: NotificationAbout15MinutesBeforeLessonUseCase =
        getKoin().get()

    override fun onReceive(context: Context, intent: Intent) {
        intent.getIntExtra(SchedulerExtra.ID_OF_REMINDER, -1).let { reminderId ->
            if (reminderId != -1) {
                // Используем runBlocking для получения напоминания из репозитория.
                runBlocking {
                    reminderRepository.getReminderByIdOfReminder(reminderId)
                }?.let {
                    // Создаем уведомление и показываем его через NotificationService.
                    when (it.type) {
                        ReminderType.AFTER_BEGINNING_LESSON -> {
                            notificationAfterBeginningLessonForBeCheckedAtThisLesson.invoke(it)
                        }

                        ReminderType.BEFORE_LESSON -> {
                            notificationAbout15MinutesBeforeLessonUseCase.invoke(it)
                        }
                    }
                }
            }
        }
    }
}
