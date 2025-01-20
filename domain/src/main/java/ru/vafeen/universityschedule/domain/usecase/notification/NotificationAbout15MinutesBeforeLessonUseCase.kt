package ru.vafeen.universityschedule.domain.usecase.notification

import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.notifications.NotificationBuilder
import ru.vafeen.universityschedule.domain.notifications.NotificationService
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

class NotificationAbout15MinutesBeforeLessonUseCase(
    private val notificationService: NotificationService,
    private val notificationBuilder: NotificationBuilder,
) : UseCase {
    operator fun invoke(reminder: Reminder) = notificationService.showNotification(
        notificationBuilder.createNotificationAbout15MinutesBeforeLesson(
            title = reminder.title,
            text = reminder.text,
        )
    )
}