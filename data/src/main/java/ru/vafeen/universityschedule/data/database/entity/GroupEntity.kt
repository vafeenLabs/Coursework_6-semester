package ru.vafeen.universityschedule.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность базы данных для представления группы в университете.
 * Содержит основную информацию о группе.
 *
 * @property id Уникальный идентификатор группы, служащий первичным ключом в таблице.
 * @property course Курс, на котором обучается группа.
 * @property group Название группы
 */
@Entity(tableName = "Group")
internal data class GroupEntity(
    @PrimaryKey val id: Int,
    val course: Int,
    val group: String
)
