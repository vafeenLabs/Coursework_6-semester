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

    private val migrations = listOf<Migration>()

    /**
     * Получение массива миграций для использования в базе данных.
     */
    fun migrations(): Array<Migration> = migrations.toTypedArray()
}