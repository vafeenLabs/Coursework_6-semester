package ru.vafeen.universityschedule.domain.converter

/**
 * Интерфейс для преобразования объектов одного типа в другой.
 *
 * @param A Тип исходного объекта.
 * @param B Тип целевого объекта.
 */
interface OneWayBaseConverter<A, B> {
    /**
     * Преобразует объект типа A в объект типа B.
     *
     * @param a Исходный объект типа A.
     * @return Преобразованный объект типа B.
     */
    fun convert(a: A): B
}