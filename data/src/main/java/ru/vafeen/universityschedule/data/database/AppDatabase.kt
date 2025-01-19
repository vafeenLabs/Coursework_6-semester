package ru.vafeen.universityschedule.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.vafeen.universityschedule.data.converters.DateTimeConverter
import ru.vafeen.universityschedule.data.converters.TimeConverter
import ru.vafeen.universityschedule.data.database.dao.GroupDao
import ru.vafeen.universityschedule.data.database.dao.LessonDao
import ru.vafeen.universityschedule.data.database.dao.ReminderDao
import ru.vafeen.universityschedule.data.database.dao.TeacherDao
import ru.vafeen.universityschedule.data.database.entity.GroupEntity
import ru.vafeen.universityschedule.data.database.entity.LessonEntity
import ru.vafeen.universityschedule.data.database.entity.ReminderEntity
import ru.vafeen.universityschedule.data.database.entity.TeacherEntity

/**
 * Основная база данных приложения, содержащая таблицы для пар и напоминаний.
 *
 * @property lessonDao DAO для работы с парами.
 * @property reminderDao DAO для работы с напоминаниями.
 */
@Database(
    exportSchema = true,
    entities = [LessonEntity::class, ReminderEntity::class, GroupEntity::class, TeacherEntity::class],
    version = 9,
)
@TypeConverters(TimeConverter::class, DateTimeConverter::class)
internal abstract class AppDatabase : RoomDatabase() {

    /**
     * Получение DAO для работы с парами.
     */
    abstract fun lessonDao(): LessonDao

    /**
     * Получение DAO для работы с напоминаниями.
     */
    abstract fun reminderDao(): ReminderDao

    /**
     * Получение DAO для работы с группами.
     */
    abstract fun groupDao(): GroupDao

    /**
     * Получение DAO для работы с преподавателями.
     */
    abstract fun teacherDao(): TeacherDao

}