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
import ru.vafeen.universityschedule.domain.usecase.CatMeowUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetReminderByIdOfReminderUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.CancelJobUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.ScheduleRepeatingJobUseCase
import java.time.LocalDate

/**
 * ViewModel для главного экрана приложения, который управляет расписанием пар,
 * напоминаниями и настройками. Обрабатывает добавление, удаление, обновление пар и напоминаний,
 * а также управление миграцией данных и задачами планировщика.
 *
 * @property getAsFlowLessonsUseCase Юзкейс для получения пар как потока данных.
 * @property getAsFlowRemindersUseCase Юзкейс для получения напоминаний как потока данных.
 * @property insertLessonsUseCase Юзкейс для добавления новых пар.
 * @property insertRemindersUseCase Юзкейс для добавления новых напоминаний.
 * @property deleteAllReminderUseCase Юзкейс для удаления всех напоминаний.
 * @property getReminderByIdOfReminderUseCase Юзкейс для получения напоминания по ID.
 * @property scheduleRepeatingJobUseCase Юзкейс для планирования повторяющихся задач.
 * @property catMeowUseCase Юзкейс для вызова "мяу" .
 * @property cancelJobUseCase Юзкейс для отмены задач.
 * @property settingsManager Менеджер для работы с настройками приложения.
 */
internal class MainScreenViewModel(
    getAsFlowLessonsUseCase: GetAsFlowLessonsUseCase,
    getAsFlowRemindersUseCase: GetAsFlowRemindersUseCase,
    private val insertLessonsUseCase: InsertLessonsUseCase,
    private val insertRemindersUseCase: InsertRemindersUseCase,
    private val deleteAllReminderUseCase: DeleteRemindersUseCase,
    private val getReminderByIdOfReminderUseCase: GetReminderByIdOfReminderUseCase,
    private val scheduleRepeatingJobUseCase: ScheduleRepeatingJobUseCase,
    private val catMeowUseCase: CatMeowUseCase,
    private val cancelJobUseCase: CancelJobUseCase,
    private val settingsManager: SettingsManager
) : ViewModel() {

    /**
     * Флаг, указывающий, проходит ли в данный момент пара.
     */
    var nowIsLesson: Boolean = false

    /**
     * Количество дней для отображения в расписании (например, 365 дней).
     */
    val pageNumber = 365

    /**
     * Текущая дата.
     */
    val todayDate: LocalDate = LocalDate.now()

    /**
     * Поток данных с парами.
     */
    val lessonsFlow = getAsFlowLessonsUseCase.invoke().map {
        it.toList()
    }

    /**
     * Поток данных с напоминаниями.
     */
    val remindersFlow = getAsFlowRemindersUseCase.invoke()

    /**
     * Поток с настройками приложения.
     */
    val settingsFlow = settingsManager.settingsFlow

    /**
     * Вызов "мяу" на котиках.
     */
    fun meow() {
        catMeowUseCase.invoke()
    }

    /**
     * Функция для обновления данных о паре.
     *
     * @param lesson Пара, данные которой нужно обновить.
     */
    fun updateLesson(lesson: Lesson) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("update", "обновление ${lesson.note}")
            insertLessonsUseCase.invoke(lesson)
        }
    }

    /**
     * Функция для добавления напоминания за 15 минут до начала пары
     * и обновления локальной базы данных.
     *
     * @param lesson Пара, для которой создается напоминание.
     * @param newReminder Новое напоминание.
     */
    suspend fun addReminderAbout15MinutesBeforeLessonAndUpdateLocalDB(
        lesson: Lesson, newReminder: Reminder
    ) {
        val newLesson = lesson.copy(idOfReminderBeforeLesson = newReminder.idOfReminder)
        insertLessonsUseCase.invoke(newLesson)
        insertRemindersUseCase.invoke(newReminder)
        scheduleRepeatingJobUseCase.invoke(reminder = newReminder)
    }

    /**
     * Функция для удаления напоминания о проверке за 15 минут до начала пары
     * и обновления локальной базы данных.
     *
     * @param lesson Пара, для которой удаляется напоминание.
     */
    suspend fun removeReminderAbout15MinutesBeforeLessonAndUpdateLocalDB(
        lesson: Lesson
    ) {
        val newLesson = lesson.copy(idOfReminderBeforeLesson = null)
        insertLessonsUseCase.invoke(newLesson)
        val reminder = getReminderByIdOfReminderUseCase.invoke(
            idOfReminder = lesson.idOfReminderBeforeLesson ?: -1
        )
        reminder?.let {
            cancelJobUseCase.invoke(reminder = it)
            deleteAllReminderUseCase.invoke(it)
        }
    }

    /**
     * Функция для сохранения настроек в SharedPreferences.
     * Принимает функцию, изменяющую текущие настройки.
     *
     * @param saving Функция, изменяющая настройки.
     */
    fun saveSettingsToSharedPreferences(saving: (Settings) -> Settings) {
        settingsManager.save(saving)
    }

    /**
     * Функция для добавления напоминания об отметке на паре
     * и обновления локальной базы данных.
     *
     * @param lesson Пара, для которой создается напоминание.
     * @param newReminder Новое напоминание.
     */
    suspend fun addReminderAboutCheckingOnLessonAndUpdateLocalDB(
        lesson: Lesson, newReminder: Reminder
    ) {
        insertLessonsUseCase.invoke(lesson.copy(idOfReminderAfterBeginningLesson = newReminder.idOfReminder))

        insertRemindersUseCase.invoke(newReminder)
        scheduleRepeatingJobUseCase.invoke(reminder = newReminder)
    }

    /**
     * Функция для удаления напоминания об отметке на паре
     * и обновления локальной базы данных.
     *
     * @param lesson Пара, для которой удаляется напоминание.
     */
    suspend fun removeReminderAboutCheckingOnLessonAndUpdateLocalDB(
        lesson: Lesson,
    ) {
        val newLesson = lesson.copy(idOfReminderAfterBeginningLesson = null)
        insertLessonsUseCase.invoke(newLesson)
        val reminder = getReminderByIdOfReminderUseCase.invoke(
            idOfReminder = lesson.idOfReminderAfterBeginningLesson ?: -1
        )
        reminder?.let {
            cancelJobUseCase.invoke(reminder = it)
            deleteAllReminderUseCase.invoke(it)
        }
    }
}
