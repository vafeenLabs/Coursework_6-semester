package ru.vafeen.universityschedule.data.impl.notifications

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import ru.vafeen.universityschedule.domain.MainActivityIntentProvider
import ru.vafeen.universityschedule.domain.notifications.NotificationBuilder
import ru.vafeen.universityschedule.domain.notifications.NotificationChannelInfo
import ru.vafeen.universityschedule.resources.R

internal class NotificationBuilderImpl(
    private val context: Context,
    private val mainActivityIntentProvider: MainActivityIntentProvider,
) : NotificationBuilder {

    override fun createNotificationAbout15MinutesBeforeLesson(
        title: String,
        text: String,
    ): Notification {
        val pendingIntent = PendingIntent.getActivity(
            context,
            NotificationChannelInfo.About15MinutesBeforeLesson.requestCode,
            mainActivityIntentProvider.provideIntent(),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // Обновляем текущий интент.
        )

        return NotificationCompat.Builder(
            context,
            NotificationChannelInfo.About15MinutesBeforeLesson.notificationChannelID // Используем ID канала.
        )
            .setSmallIcon(R.drawable.message)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // Устанавливаем звук.
            .setContentIntent(pendingIntent)
            .build()
    }

    override fun createNotificationAfterBeginningLessonForBeCheckedAtThisLesson(
        title: String,
        text: String,
    ): Notification {
        val pendingIntent = PendingIntent.getActivity(
            context,
            NotificationChannelInfo.AfterStartingLesson.requestCode,
            mainActivityIntentProvider.provideIntent(),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(
            context,
            NotificationChannelInfo.AfterStartingLesson.notificationChannelID // Используем ID канала.
        )
            .setSmallIcon(R.drawable.notification_about_checking)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // Устанавливаем звук.
            .setContentIntent(pendingIntent)
            .build()
    }

    override fun createNotificationReminderRecovery(
        title: String,
        text: String,
    ): Notification {
        val pendingIntent = PendingIntent.getActivity(
            context,
            NotificationChannelInfo.ReminderRecovery.requestCode,
            mainActivityIntentProvider.provideIntent(),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT // Обновляем текущий интент.
        )

        return NotificationCompat.Builder(
            context,
            NotificationChannelInfo.ReminderRecovery.notificationChannelID // Используем ID канала.
        )
            .setSmallIcon(R.drawable.reminder)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // Устанавливаем звук.
            .setContentIntent(pendingIntent)
            .build()
    }
}
