package ru.vafeen.universityschedule.domain.models

/**
 * Модель для представления группы в университете.
 * Содержит основную информацию о группе.
 *
 * @property id Уникальный идентификатор группы.
 * @property course Курс, на котором обучается группа.
 * @property group Название группы
 */
data class Group(
    val id: Int,
    val course: Int,
    val group: String
)
