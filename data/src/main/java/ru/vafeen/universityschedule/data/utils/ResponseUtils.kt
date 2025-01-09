package ru.vafeen.universityschedule.data.utils

import ru.vafeen.universityschedule.data.database.entity.LessonEntity
import ru.vafeen.universityschedule.data.network.dto.googlesheets_service.ResponseWrapper
import ru.vafeen.universityschedule.data.network.dto.googlesheets_service.RowDTO

/**
 * Преобразует объект [RowDTO] в объект [LessonEntity].
 *
 * @return [LessonEntity] или null, если преобразование не удалось.
 */
internal fun RowDTO.toLesson(): LessonEntity? = this.cells.map {
    it?.value // Получаем значения ячеек
}.let {
    try {
        LessonEntity(
            dayOfWeek = it.getOrNull(0)?.toDayOfWeek(), // Преобразуем первый элемент в день недели
            name = it.getOrNull(1).makeNullIfNull(), // Получаем название урока
            startTime = "${it[2]}".toTimeOfLessonAsLocalTime(), // Преобразуем время начала урока
            endTime = "${it[3]}".toTimeOfLessonAsLocalTime(), // Преобразуем время окончания урока
            classroom = it.getOrNull(4).makeNullIfNull(), // Получаем информацию о классе
            teacher = it.getOrNull(5).makeNullIfNull(), // Получаем информацию о преподавателе
            subGroup = it.getOrNull(6).makeNullIfNull()?.normalizeCase(), // Получаем информацию о подгруппе
            frequency = it.getOrNull(7).makeNullIfNull()?.toFrequencyString(), // Получаем частоту урока
            linkToCourse = it.getOrNull(8).makeNullIfNull(), // Получаем ссылку на курс в мудле
        )
    } catch (e: Exception) {
        null // Возвращаем null в случае ошибки преобразования
    }
}

/**
 * Преобразует объект [ResponseWrapper] в список объектов [LessonEntity].
 *
 * @return Список [LessonEntity], содержащий все уроки из ответа.
 */
internal fun ResponseWrapper.toLessonsList(): List<LessonEntity> {
    val result = mutableListOf<LessonEntity>()
    for (row in this.table.rows) {
        row.toLesson()?.let { result.add(element = it) } // Добавляем каждое преобразованное занятие в список
    }
    return result // Возвращаем список уроков
}