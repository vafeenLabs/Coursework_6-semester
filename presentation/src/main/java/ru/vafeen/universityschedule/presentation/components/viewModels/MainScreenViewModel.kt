package ru.vafeen.universityschedule.presentation.components.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.models.Reminder
import ru.vafeen.universityschedule.domain.models.Settings
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.usecase.db.DeleteRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetReminderByIdOfReminderUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.UpdateLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.CancelJobUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.ScheduleRepeatingJobUseCase
import java.time.LocalDate


internal class MainScreenViewModel(
    getAsFlowLessonsUseCase: GetAsFlowLessonsUseCase,
    getAsFlowRemindersUseCase: GetAsFlowRemindersUseCase,
    private val insertLessonsUseCase: InsertLessonsUseCase,
    private val insertRemindersUseCase: InsertRemindersUseCase,
    private val deleteAllReminderUseCase: DeleteRemindersUseCase,
    private val getReminderByIdOfReminderUseCase: GetReminderByIdOfReminderUseCase,
    private val scheduleRepeatingJobUseCase: ScheduleRepeatingJobUseCase,
    private val cancelJobUseCase: CancelJobUseCase,
    private val updateLessonsUseCase: UpdateLessonsUseCase,
    private val settingsManager: SettingsManager
) : ViewModel() {
    var nowIsLesson: Boolean = false
    val pageNumber = 365
    val todayDate: LocalDate = LocalDate.now()

    val lessonsFlow = getAsFlowLessonsUseCase.use().map {
        it.toList()
    }
    val remindersFlow = getAsFlowRemindersUseCase.use()

    val settingsFlow = settingsManager.settingsFlow

    fun updateLesson(lesson: Lesson) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("update", "обновление ${lesson.note}")
            updateLessonsUseCase.use(lesson)
        }
    }


    suspend fun addReminderAbout15MinutesBeforeLessonAndUpdateLocalDB(
        lesson: Lesson, newReminder: Reminder
    ) {
        val newLesson = lesson.copy(idOfReminderBeforeLesson = newReminder.idOfReminder)
        insertLessonsUseCase.use(newLesson)
        insertRemindersUseCase.use(newReminder)
        scheduleRepeatingJobUseCase.use(reminder = newReminder)
    }

    suspend fun removeReminderAbout15MinutesBeforeLessonAndUpdateLocalDB(
        lesson: Lesson
    ) {
        val newLesson = lesson.copy(idOfReminderBeforeLesson = null)
        insertLessonsUseCase.use(newLesson)
        val reminder = getReminderByIdOfReminderUseCase.use(
            idOfReminder = lesson.idOfReminderBeforeLesson ?: -1
        )
        reminder?.let {
            cancelJobUseCase.use(reminder = it)
            deleteAllReminderUseCase.use(it)
        }
    }

    fun saveSettingsToSharedPreferences(saving: (Settings) -> Settings) {
        settingsManager.save(saving)
    }

    suspend fun addReminderAboutCheckingOnLessonAndUpdateLocalDB(
        lesson: Lesson, newReminder: Reminder
    ) {
        insertLessonsUseCase.use(lesson.copy(idOfReminderAfterBeginningLesson = newReminder.idOfReminder))

        insertRemindersUseCase.use(newReminder)
        scheduleRepeatingJobUseCase.use(reminder = newReminder)
    }

    suspend fun removeReminderAboutCheckingOnLessonAndUpdateLocalDB(
        lesson: Lesson,
    ) {
        val newLesson = lesson.copy(idOfReminderAfterBeginningLesson = null)
        insertLessonsUseCase.use(newLesson)
        val reminder = getReminderByIdOfReminderUseCase.use(
            idOfReminder = lesson.idOfReminderAfterBeginningLesson ?: -1
        )
        reminder?.let {
            cancelJobUseCase.use(reminder = it)
            deleteAllReminderUseCase.use(it)
        }
    }
}