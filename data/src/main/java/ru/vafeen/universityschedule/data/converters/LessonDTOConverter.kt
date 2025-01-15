package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.backenddto.LessonDTO
import ru.vafeen.universityschedule.domain.converter.OneWayBaseConverter
import ru.vafeen.universityschedule.domain.models.Lesson

/**
 * Конвертер для преобразования [LessonDTO] в [Lesson] и обратно.
 *
 * Используется для преобразования данных между DTO и моделью доменного уровня.
 */
internal class LessonDTOConverter(private val frequencyOneWayConverter: FrequencyOneWayConverter) :
    OneWayBaseConverter<LessonDTO, Lesson> {

    /**
     * Преобразует [LessonDTO] в [Lesson].
     *
     * @param a Экземпляр [LessonDTO], полученный от сервера.
     * @return Экземпляр [Lesson], используемый на уровне доменной модели.
     */
    override fun convert(a: LessonDTO): Lesson = Lesson(
        id = 0, // Идентификатор не передается в DTO
        dayOfWeek = a.dayOfWeek,
        name = a.name,
        startTime = a.startTime,
        endTime = a.endTime,
        classroom = a.classroom,
        teacher = a.teacher,
        subGroup = a.subGroup,
        frequency = a.frequency?.let { frequencyOneWayConverter.convert(it) },
    )
}
