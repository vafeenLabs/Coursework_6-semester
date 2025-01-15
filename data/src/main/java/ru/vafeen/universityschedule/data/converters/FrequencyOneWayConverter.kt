package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.data.utils.normalizeCase
import ru.vafeen.universityschedule.domain.converter.OneWayBaseConverter
import ru.vafeen.universityschedule.domain.models.model_additions.Frequency

/**
 * Односторонний конвертер для преобразования строки частоты занятий в тип Frequency.
 * Преобразует строку в соответствующий тип частоты (числитель, знаменатель или каждую неделю).
 */
internal class FrequencyOneWayConverter : OneWayBaseConverter<String, Frequency> {

    /**
     * Преобразует строку частоты в тип Frequency.
     *
     * @param a Строка, содержащая информацию о частоте занятий.
     * @return Соответствующий тип частоты (Numerator, Denominator или Every).
     */
    override fun convert(a: String): Frequency = when (a.normalizeCase()) {
        "Числитель" -> Frequency.Numerator
        "Знаменатель" -> Frequency.Denominator
        else -> Frequency.Every
    }
}
