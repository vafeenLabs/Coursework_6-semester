package ru.vafeen.universityschedule.backenddto

/**
 * ДТО (Data Transfer Object) для представления группы в университете.
 * Содержит основную информацию о группе.
 *
 * @property id Уникальный идентификатор группы.
 * @property course Курс, на котором обучается группа.
 * @property group Название группы
 */
data class GroupDTO(
    val id: Int,
    val course: Int,
    val group: String
)
