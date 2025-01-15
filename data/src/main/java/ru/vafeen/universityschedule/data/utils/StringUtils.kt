package ru.vafeen.universityschedule.data.utils

import java.util.Locale


/**
 * Нормализует регистр строки, делая первую букву заглавной и все остальные строчными.
 *
 * @return Нормализованная строка.
 */
fun String.normalizeCase(): String = this.lowercase()
    .replaceFirstChar { it.titlecase(Locale.ROOT) }



