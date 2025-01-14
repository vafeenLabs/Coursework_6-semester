package ru.vafeen.universityschedule.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Менеджер миграций базы данных приложения.
 *
 * Этот класс управляет миграциями базы данных, обеспечивая обновление структуры таблиц
 * при изменении версии базы данных.
 */
internal class AppDatabaseMigrationManager {
    private fun createMigration(
        startVersion: Int, endVersion: Int, migrate: (db: SupportSQLiteDatabase) -> Unit
    ): Migration = object : Migration(startVersion, endVersion) {
        override fun migrate(db: SupportSQLiteDatabase) = migrate(db)
    }

    private val migrations = listOf(
        createMigration(1, 2) { db ->
            // Создание новой таблицы Reminder
            db.execSQL(
                """
                    CREATE TABLE IF NOT EXISTS `Reminder` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `idOfReminder` INTEGER NOT NULL,
                        `title` TEXT NOT NULL,
                        `text` TEXT NOT NULL,
                        `dt` INTEGER NOT NULL
                    )
                    """.trimIndent()
            )

            // Добавление нового столбца в таблицу Lesson
            db.execSQL(
                """
                    ALTER TABLE `Lesson` ADD COLUMN `idOfReminder` INTEGER
                    """.trimIndent()
            )
        },
        createMigration(2, 3) { db ->
            db.execSQL("ALTER TABLE `Lesson` ADD COLUMN `idOfReminderAfterBeginningLesson` INTEGER")
            db.execSQL("ALTER TABLE `Lesson` RENAME COLUMN `idOfReminder` TO `idOfReminderBeforeLesson`")
        },
        createMigration(3, 4) { db ->
            db.execSQL("ALTER TABLE reminder ADD COLUMN type TEXT NOT NULL DEFAULT 'AFTER_BEGINNING_LESSON'")
        },
        createMigration(4, 5) { db ->
            db.execSQL("ALTER TABLE reminder ADD COLUMN duration TEXT NOT NULL DEFAULT 'EVERY_WEEK'")
        },
        createMigration(5, 6) { db ->
            db.execSQL("ALTER TABLE lesson ADD COLUMN note TEXT DEFAULT NULL")
        },
        createMigration(6, 7) { db ->
            db.execSQL(
                """
                ALTER TABLE `Lesson` ADD COLUMN `linkToCourse` TEXT
                    """.trimIndent()
            )
        },
        createMigration(7, 8) { db ->
            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS `Group`(
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `course` INTEGER NOT NULL,
                `group` TEXT NOT NULL
                )
            """.trimIndent()
            )
        }
    )

    /**
     * Получение массива миграций для использования в базе данных.
     */
    fun migrations(): Array<Migration> = migrations.toTypedArray()
}