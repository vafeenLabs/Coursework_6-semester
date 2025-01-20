package ru.vafeen.universityschedule.data.impl.database

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.vafeen.universityschedule.data.converters.ReminderConverter
import ru.vafeen.universityschedule.data.database.AppDatabase
import ru.vafeen.universityschedule.domain.database.ReminderLocalRepository
import ru.vafeen.universityschedule.domain.models.Reminder

/**
 * Реализация репозитория для работы с напоминаниями в базе данных.
 *
 * @property reminderConverter Конвертер для преобразования объектов Reminder между слоями.
 * @property db База данных приложения.
 */
internal class ReminderLocalRepositoryImpl(
    private val reminderConverter: ReminderConverter,
    private val db: AppDatabase
) : ReminderLocalRepository {

    private val reminderDao = db.reminderDao()

    /**
     * Получение списка напоминаний в виде Flow.
     *
     * @return Flow, содержащий список напоминаний.
     */
    override fun getAsFlowReminders(): Flow<List<Reminder>> =
        reminderDao.getAllAsFlow().map {
            reminderConverter.convertABList(it)
        }

    /**
     * Получение напоминания по идентификатору.
     *
     * @param idOfReminder Идентификатор напоминания.
     * @return Напоминание или null, если не найдено.
     */
    override suspend fun getReminderByIdOfReminder(idOfReminder: Int): Reminder? =
        reminderDao.getReminderByIdOfReminder(idOfReminder)?.let { reminderConverter.convertAB(it) }

    /**
     * Вставка списка напоминаний в базу данных.
     *
     * @param reminders Список напоминаний для вставки.
     */
    override suspend fun insertReminders(reminders: List<Reminder>) =
        reminderDao.insert(reminders.map { reminderConverter.convertBA(it) }.also {
            Log.d("insert", it.joinToString { r -> "$r\n" })
        })

    /**
     * Удаление списка напоминаний из базы данных.
     *
     * @param reminders Список напоминаний для удаления.
     */
    override suspend fun deleteReminders(reminders: List<Reminder>) =
        reminderDao.delete(reminders.map { reminderConverter.convertBA(it) })
}
