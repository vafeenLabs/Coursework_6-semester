package ru.vafeen.universityschedule.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalTime

/**
 * Сущность для представления пары в базе данных.
 *
 * Содержит подробную информацию о паре, включая день недели, время начала и окончания, аудиторию,
 * преподавателя и другие атрибуты.
 *
 * @property id Уникальный идентификатор пары (генерируется автоматически).
 * @property dayOfWeek День недели, в который проходит пара (не может быть null).
 * @property name Название пары (не может быть null).
 * @property startTime Время начала пары (не может быть null).
 * @property endTime Время окончания пары (не может быть null).
 * @property classroom Аудитория, в которой проходит пара (может быть null).
 * @property teacher Имя преподавателя, проводящего пару (не может быть null).
 * @property subGroup Подгруппа, для которой предназначена пара (может быть null).
 * @property groupId Идентификатор группы, к которой относится пара (не может быть null).
 * @property frequency Частота проведения пары (например, "каждую неделю", "через неделю" и т.д., может быть null).
 * @property idOfReminderBeforeLesson Идентификатор напоминания перед парой (может быть null).
 * @property idOfReminderAfterBeginningLesson Идентификатор напоминания после начала пары (может быть null).
 * @property note Заметки пользователя к паре (может быть null).
 * @property linkToCourse Ссылка на курс, связанный с парой (может быть null).
 */
@Entity(tableName = "Lesson")
internal data class LessonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dayOfWeek: DayOfWeek,
    val name: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val classroom: String? = null,
    val teacher: String,
    val subGroup: String? = null,
    val groupId: Int,
    val frequency: String? = null,
    val idOfReminderBeforeLesson: Int? = null,
    val idOfReminderAfterBeginningLesson: Int? = null,
    val note: String? = null,
    val linkToCourse: String? = null
) : Comparable<LessonEntity> {

    /**
     * Переопределение метода toString для удобного отображения информации о паре.
     *
     * Возвращает строку с основной информацией о паре, включая день недели, название,
     * время начала и окончания, аудиторию, преподавателя, подгруппу и частоту.
     *
     * @return Строковое представление объекта [LessonEntity].
     */
    override fun toString(): String {
        return "\nLessonEntity(id=$id, " +
                "dayOfWeek=$dayOfWeek, " +
                "name=$name, " +
                "startTime=$startTime, " +
                "endTime=$endTime, " +
                "classroom=${classroom ?: "\"is null\""}, " +
                "teacher=$teacher, " +
                "subGroup=${subGroup ?: "\"is null\""}, " +
                "groupId=$groupId, " +
                "frequency=${frequency ?: "\"is null\""}" +
                ")"
    }

    /**
     * Сравнение двух пар по времени начала.
     *
     * @param other Другая пара для сравнения.
     * @return 1, если текущая пара начинается позже; 0, если в одно и то же время; -1, если раньше.
     */
    override fun compareTo(other: LessonEntity): Int = when {
        startTime > other.startTime -> 1
        startTime == other.startTime -> 0
        else -> -1
    }
}
