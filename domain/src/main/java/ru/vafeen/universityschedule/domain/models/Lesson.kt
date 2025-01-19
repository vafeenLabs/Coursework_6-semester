package ru.vafeen.universityschedule.domain.models

import ru.vafeen.universityschedule.domain.models.model_additions.Frequency
import java.time.DayOfWeek
import java.time.LocalTime

/**
 * Модель для представления пары в расписании.
 *
 * Содержит подробную информацию о паре, включая день недели, время начала и окончания, аудиторию,
 * преподавателя и другие атрибуты.
 *
 * @property id Уникальный идентификатор пары.
 * @property dayOfWeek День недели, в который проходит пара (не может быть null).
 * @property name Название пары (не может быть null).
 * @property startTime Время начала пары (не может быть null).
 * @property endTime Время окончания пары (не может быть null).
 * @property classroom Номер аудитории, в которой проходит пара (может быть null).
 * @property teacher Имя преподавателя (не может быть null).
 * @property subGroup Подгруппа, к которой относится пара (может быть null).
 * @property groupId Идентификатор группы, к которой относится пара (не может быть null).
 * @property frequency Частота проведения пары (может быть null).
 * @property idOfReminderBeforeLesson Идентификатор напоминания перед парой (может быть null).
 * @property idOfReminderAfterBeginningLesson Идентификатор напоминания после начала пары (может быть null).
 * @property note Примечание к паре (может быть null).
 * @property linkToCourse Ссылка на курс, связанный с парой (может быть null).
 */
data class Lesson(
    val id: Int = 0,
    val dayOfWeek: DayOfWeek,
    val name: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val classroom: String? = null,
    val teacher: String,
    val subGroup: String? = null,
    val groupId: Int,
    val frequency: Frequency? = null,
    val idOfReminderBeforeLesson: Int? = null,
    val idOfReminderAfterBeginningLesson: Int? = null,
    val note: String? = null,
    val linkToCourse: String? = null
) : Comparable<Lesson> {

    /**
     * Возвращает строковое представление объекта [Lesson].
     *
     * Включает информацию о дне недели, названии, времени начала и окончания, аудитории,
     * преподавателе, подгруппе, частоте и ссылке на курс.
     *
     * @return Строковое представление объекта [Lesson].
     */
    override fun toString(): String {
        return "\nLesson(id=$id, " +
                "dayOfWeek=$dayOfWeek, " +
                "name=${name ?: "\"is null\""}, " +
                "startTime=$startTime, " +
                "endTime=$endTime, " +
                "classroom=${classroom ?: "\"is null\""}, " +
                "teacher=$teacher, " +
                "subGroup=${subGroup ?: "\"is null\""}, " +
                "groupId=$groupId, " +
                "frequency=${frequency ?: "\"is null\""}, " +
                "idOfReminderBeforeLesson=${idOfReminderBeforeLesson ?: "\"is null\""}, " +
                "idOfReminderAfterBeginningLesson=${idOfReminderAfterBeginningLesson ?: "\"is null\""}, " +
                "note=${note ?: "\"is null\""}, " +
                "linkToCourse=${linkToCourse ?: "\"is null\""}" +
                ")"
    }

    /**
     * Сравнивает текущий объект [Lesson] с другим объектом [Lesson] по времени начала.
     *
     * @param other Другой объект [Lesson] для сравнения.
     * @return 1, если время начала текущей пары позже; 0, если равно; -1, если раньше.
     */
    override fun compareTo(other: Lesson): Int = when {
        startTime > other.startTime -> 1
        startTime == other.startTime -> 0
        else -> -1
    }
}
