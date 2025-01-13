package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.data.utils.normalizeCase
import ru.vafeen.universityschedule.data.utils.removeSpaces
import ru.vafeen.universityschedule.domain.converter.OneWayBaseConverter
import java.time.DayOfWeek

/**
 * Конвертер для преобразования строки в объект [DayOfWeek].
 *
 * Используется для сопоставления строк, представляющих дни недели,
 * с соответствующими значениями перечисления [DayOfWeek].
 *
 * Пример входных данных:
 * - "Понедельник" -> [DayOfWeek.MONDAY]
 * - "Вторник" -> [DayOfWeek.TUESDAY]
 * - "Среда" -> [DayOfWeek.WEDNESDAY]
 * - "Четверг" -> [DayOfWeek.THURSDAY]
 * - "Пятница" -> [DayOfWeek.FRIDAY]
 * - "Суббота" -> [DayOfWeek.SATURDAY]
 * - Любой другой текст -> [DayOfWeek.SUNDAY]
 *
 * Входная строка нормализуется с помощью методов [normalizeCase] (приведение к нижнему регистру)
 * и [removeSpaces] (удаление всех пробелов).
 */
internal class DayOfWeekConverter : OneWayBaseConverter<String, DayOfWeek> {

    /**
     * Преобразует строку в объект [DayOfWeek].
     *
     * @param a строка, представляющая день недели (например, "Понедельник", "Вторник").
     * @return Объект [DayOfWeek], соответствующий дню недели. Если строка не распознана,
     * возвращается [DayOfWeek.SUNDAY] по умолчанию.
     */
    override fun convert(a: String): DayOfWeek = when (a.normalizeCase().removeSpaces()) {
        "Понедельник" -> DayOfWeek.MONDAY
        "Вторник" -> DayOfWeek.TUESDAY
        "Среда" -> DayOfWeek.WEDNESDAY
        "Четверг" -> DayOfWeek.THURSDAY
        "Пятница" -> DayOfWeek.FRIDAY
        "Суббота" -> DayOfWeek.SATURDAY
        else -> DayOfWeek.SUNDAY
    }
}
