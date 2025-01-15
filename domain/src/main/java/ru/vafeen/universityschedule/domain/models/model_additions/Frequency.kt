package ru.vafeen.universityschedule.domain.models.model_additions

import ru.vafeen.universityschedule.resources.R

/**
 * Перечисление для представления частоты проведения занятий.
 * Каждый вариант имеет ресурс строки для отображения в интерфейсе.
 *
 * @property resourceName Идентификатор ресурса строки для отображения частоты.
 */
enum class Frequency(val resourceName: Int) {
    Every(resourceName = R.string.every) {
        override fun getOpposite(): Frequency = Every
    },
    Numerator(resourceName = R.string.numerator) {
        override fun getOpposite(): Frequency = Denominator
    },
    Denominator(resourceName = R.string.denominator) {
        override fun getOpposite(): Frequency = Numerator
    };

    /**
     * Абстрактный метод для получения противоположной частоты.
     * Должен быть реализован для каждого варианта перечисления.
     */
    abstract fun getOpposite(): Frequency
}
