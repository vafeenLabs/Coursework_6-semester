package ru.vafeen.universityschedule.data.utils

import java.time.LocalTime
import java.util.Locale

/**
 * Удаляет подстроки из строки.
 *
 * @param substrings Подстроки, которые нужно удалить.
 * @return Строка без указанных подстрок.
 */
internal fun String.removeSubStrings(vararg substrings: String): String {
    var result = this
    substrings.forEach {
        result = result.replace(it, "")
    }
    return result
}


/**
 * Преобразует строку в целое число.
 *
 * @return Целое число, соответствующее строке, или 0 в случае ошибки.
 */
internal fun String.toMyInt(): Int {
    val thisWithoutSpaces = this.removeSpaces()
    try {
        return thisWithoutSpaces.toInt()
    } catch (e: Exception) {
        return when (thisWithoutSpaces) {
            "01" -> 1
            "02" -> 2
            "03" -> 3
            "04" -> 4
            "05" -> 5
            "06" -> 6
            "07" -> 7
            "08" -> 8
            "09" -> 9
            else -> 0
        }
    }
}

/**
 * Удаляет пробелы из строки.
 *
 * @return Строка без пробелов.
 */
internal fun String.removeSpaces() = this.removeSubStrings(" ")

/**
 * Преобразует строку времени урока в объект [LocalTime].
 *
 * @return [LocalTime] объект, представляющий время урока.
 */
internal fun String.toTimeOfLessonAsLocalTime(): LocalTime {
    val list = this.removeSubStrings(" ").split(":")
    return LocalTime.of(list[0].toMyInt(), list[1].toMyInt())
}

/**
 * Нормализует регистр строки, делая первую букву заглавной и все остальные строчными.
 *
 * @return Нормализованная строка.
 */
fun String.normalizeCase(): String = this.lowercase()
    .replaceFirstChar { it.titlecase(Locale.ROOT) }




/**
 * Преобразует строку в null, если она содержит "null".
 *
 * @return null, если строка содержит "null", иначе оригинальная строка.
 */
internal fun String?.makeNullIfNull(): String? = if (this?.contains("null") == true) null else this


