package ru.vafeen.universityschedule.data.impl.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import org.koin.core.component.KoinComponent
import ru.vafeen.universityschedule.data.converters.DateTimeConverter
import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.scheduler.Scheduler
import ru.vafeen.universityschedule.domain.scheduler.SchedulerExtra
import java.time.LocalDateTime

internal class SchedulerImpl(
    private val context: Context,
    private val dtConverter: DateTimeConverter
) : Scheduler, KoinComponent {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val intent = Intent(context, ReminderReceiver::class.java)


    override fun scheduleRepeatingJob(reminder: Reminder) {
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminder.idOfReminder,
            intent.also {
                it.putExtra(SchedulerExtra.ID_OF_REMINDER, reminder.idOfReminder)
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + calculateInitialDelay(reminder),
            reminder.duration.duration.milliSeconds,
            pendingIntent
        )
    }

    override fun scheduleOneTimeJob(reminder: Reminder) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + calculateInitialDelay(reminder),
            PendingIntent.getBroadcast(
                context,
                reminder.idOfReminder,
                intent.also {
                    it.putExtra(SchedulerExtra.ID_OF_REMINDER, reminder.idOfReminder)
                },
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
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

    private fun calculateInitialDelay(reminder: Reminder): Long {
        val now = dtConverter.convertAB(LocalDateTime.now())
        val reminderTime = dtConverter.convertAB(reminder.dt)
        return if (reminderTime > now) reminderTime - now else 0L
    }

}


