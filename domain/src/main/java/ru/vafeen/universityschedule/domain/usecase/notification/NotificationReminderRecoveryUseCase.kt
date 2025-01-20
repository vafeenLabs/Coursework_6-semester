package ru.vafeen.universityschedule.domain.usecase.notification

import android.content.Context
import ru.vafeen.universityschedule.domain.notifications.NotificationBuilder
import ru.vafeen.universityschedule.domain.notifications.NotificationService
import ru.vafeen.universityschedule.domain.usecase.base.UseCase
import ru.vafeen.universityschedule.resources.R

class NotificationReminderRecoveryUseCase(
    private val context: Context,
    private val notificationService: NotificationService,
    private val notificationBuilder: NotificationBuilder
) : UseCase {
    operator fun invoke() {
        notificationService.showNotification(
            notificationBuilder.createNotificationReminderRecovery(
                title = context.getString(R.string.reminder_recovery),
                text = context.getString(R.string.reminders_restored)
            )
        )
    }
}