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
 * Основная база данных приложения, которая управляет таблицами для пар, напоминаний, групп и преподавателей.
 *
 * @property lessonDao DAO для работы с парами (LessonEntity).
 * @property reminderDao DAO для работы с напоминаниями (ReminderEntity).
 * @property groupDao DAO для работы с группами (GroupEntity).
 * @property teacherDao DAO для работы с преподавателями (TeacherEntity).
 *
 * @constructor Создает экземпляр базы данных.
 *
 * @see LessonDao для операций с парами.
 * @see ReminderDao для операций с напоминаниями.
 * @see GroupDao для операций с группами.
 * @see TeacherDao для операций с преподавателями.
 */
@Database(
    exportSchema = true,
    entities = [LessonEntity::class, ReminderEntity::class, GroupEntity::class, TeacherEntity::class],
    version = 1,
)
@TypeConverters(TimeConverter::class, DateTimeConverter::class)
internal abstract class AppDatabase : RoomDatabase() {

    /**
     * Получение DAO для работы с парами.
     *
     * @return экземпляр [LessonDao] для выполнения операций с парами.
     */
    abstract fun lessonDao(): LessonDao

    /**
     * Получение DAO для работы с напоминаниями.
     *
     * @return экземпляр [ReminderDao] для выполнения операций с напоминаниями.
     */
    abstract fun reminderDao(): ReminderDao

    /**
     * Получение DAO для работы с группами.
     *
     * @return экземпляр [GroupDao] для выполнения операций с группами.
     */
    abstract fun groupDao(): GroupDao

    /**
     * Получение DAO для работы с преподавателями.
     *
     * @return экземпляр [TeacherDao] для выполнения операций с преподавателями.
     */
    abstract fun teacherDao(): TeacherDao

}