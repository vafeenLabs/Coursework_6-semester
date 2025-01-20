package ru.vafeen.universityschedule.data.impl.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.notification.NotificationReminderRecoveryUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.CancelJobUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.ScheduleRepeatingJobUseCase

class ReminderRecoveryReceiver : BroadcastReceiver(), KoinComponent {
    private val getAsFlowRemindersUseCase: GetAsFlowRemindersUseCase = getKoin().get()
    private val cancelJobUseCase: CancelJobUseCase = getKoin().get()
    private val scheduleRepeatingJobUseCase: ScheduleRepeatingJobUseCase = getKoin().get()
    private val notificationReminderRecoveryUseCase: NotificationReminderRecoveryUseCase =
        getKoin().get()

    override fun onReceive(context: Context?, intent: Intent?) {
        runBlocking {
            getAsFlowRemindersUseCase.invoke().first()
        }.forEach {
            cancelJobUseCase.invoke(it)
            scheduleRepeatingJobUseCase.invoke(it)
            notificationReminderRecoveryUseCase.invoke()
        }
    }

}