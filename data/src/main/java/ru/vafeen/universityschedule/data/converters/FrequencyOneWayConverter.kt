package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.data.utils.normalizeCase
import ru.vafeen.universityschedule.domain.converter.OneWayBaseConverter
import ru.vafeen.universityschedule.domain.models.model_additions.Frequency


internal class FrequencyOneWayConverter : OneWayBaseConverter<String, Frequency> {
    override fun convert(a: String): Frequency = when (a.normalizeCase()) {
        "Числитель" -> Frequency.Numerator
        "Знаменатель" -> Frequency.Denominator
        else -> Frequency.Every
    }
}