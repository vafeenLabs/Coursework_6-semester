package ru.vafeen.universityschedule.data.impl.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import ru.vafeen.universityschedule.data.converters.DateTimeConverter
import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.scheduler.Scheduler
import java.time.LocalDateTime

internal class SchedulerImpl(
    private val context: Context,
    private val dtConverter: DateTimeConverter
) : Scheduler {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val intent = Intent(context, ReminderReceiver::class.java)
    private fun Reminder.createPendingIntentWithExtras() = PendingIntent.getBroadcast(
        context,
        idOfReminder,
        ReminderReceiver.withExtras(intent, this),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    override fun scheduleRepeatingJob(reminder: Reminder) {
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + calculateInitialDelay(reminder),
            reminder.duration.duration.milliSeconds,
            reminder.createPendingIntentWithExtras()
        )
    }

    override fun scheduleOneTimeJob(reminder: Reminder) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + calculateInitialDelay(reminder),
            reminder.createPendingIntentWithExtras()
        )
    }

    override fun cancelJob(reminder: Reminder) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                reminder.idOfReminder,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    private fun calculateInitialDelay(reminder: Reminder): Long =
        dtConverter.convertAB(reminder.dt) - dtConverter.convertAB(LocalDateTime.now())


}


