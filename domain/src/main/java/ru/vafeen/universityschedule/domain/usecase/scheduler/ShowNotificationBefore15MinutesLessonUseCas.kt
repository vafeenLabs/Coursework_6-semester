package ru.vafeen.universityschedule.domain.usecase.scheduler

import ru.vafeen.universityschedule.domain.notifications.NotificationBuilder
import ru.vafeen.universityschedule.domain.notifications.NotificationService

class ShowNotificationBefore15MinutesLessonUseCas(
    private val notificationBuilder: NotificationBuilder,
    private val notificationService: NotificationService
) {

    operator fun invoke(title: String, text: String) {
        notificationService.showNotification(
            notificationBuilder.createNotificationAfterBeginningLessonForBeCheckedAtThisLesson(
                title,
                text
            )
        )
    }
}