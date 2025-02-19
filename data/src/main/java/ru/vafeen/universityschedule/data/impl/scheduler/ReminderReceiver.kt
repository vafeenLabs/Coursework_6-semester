package ru.vafeen.universityschedule.data.impl.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import org.koin.core.component.KoinComponent
import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.models.model_additions.ReminderType
import ru.vafeen.universityschedule.domain.usecase.scheduler.ShowNotificationAboutLessonUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.ShowNotificationBefore15MinutesLessonUseCas

class ReminderReceiver : BroadcastReceiver(), KoinComponent {
    private val showNotificationAboutLessonUseCase by lazy {
        getKoin().get<ShowNotificationAboutLessonUseCase>()
    }
    private val showNotificationBefore15MinutesLessonUseCas by lazy {
        getKoin().get<ShowNotificationBefore15MinutesLessonUseCas>()
    }

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(TYPE_KEY)
        val title = intent.getStringExtra(TITLE_KEY).toString()
        val text = intent.getStringExtra(TEXT_KEY).toString()
        if (type == ReminderType.AFTER_BEGINNING_LESSON.name) {
            showNotificationAboutLessonUseCase.invoke(title, text)
        } else if (type == ReminderType.BEFORE_LESSON.name) {
            showNotificationBefore15MinutesLessonUseCas.invoke(title, text)
        }
    }

    companion object {
        private const val TITLE_KEY = "TITLE"
        private const val TEXT_KEY = "TEXT"
        private const val TYPE_KEY = "TYPE_KEY"
        fun withExtras(intent: Intent, reminder: Reminder): Intent = intent.also {
            it.putExtra(TITLE_KEY, reminder.title)
            it.putExtra(TEXT_KEY, reminder.text)
            it.putExtra(TYPE_KEY, reminder.type.name)
        }
    }
}