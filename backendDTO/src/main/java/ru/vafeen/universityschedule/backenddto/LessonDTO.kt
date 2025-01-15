package ru.vafeen.universityschedule.backenddto

import java.time.DayOfWeek
import java.time.LocalTime

/**
 * ДТО (Data Transfer Object) для представления занятия в расписании университета.
 * Содержит информацию о дне недели, названии занятия, времени начала и конца, аудитории, преподавателе, идентификаторе группы и других атрибутах.
 *
 * @property dayOfWeek День недели, на который назначено занятие.
 * @property name Название занятия.
 * @property startTime Время начала занятия.
 * @property endTime Время окончания занятия.
 * @property classroom Номер аудитории, где проходит занятие.
 * @property teacher ФИО преподавателя, ведущего занятие.
 * @property groupId Идентификатор группы, для которой назначено занятие.
 * @property subGroup Подгруппа, для которой назначено занятие (если применимо).
 * @property frequency Частота проведения занятия (например, "каждую неделю", "через неделю" и т.д.).
 */
data class LessonDTO(
    val dayOfWeek: DayOfWeek? = null,
    val name: String? = null,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val classroom: String? = null,
    val teacher: String? = null,
    val groupId: Int,
    val subGroup: String? = null,
    val frequency: String? = null,
)
