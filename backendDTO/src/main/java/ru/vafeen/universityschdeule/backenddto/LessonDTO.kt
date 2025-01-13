package ru.vafeen.universityschdeule.backenddto

import java.time.DayOfWeek
import java.time.LocalTime

data class LessonDTO(
    val dayOfWeek: DayOfWeek? = null,
    val name: String? = null,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val classroom: String? = null,
    val teacher: String? = null,
    val group: String? = null,
    val subGroup: String? = null,
    val frequency: String? = null,
)
