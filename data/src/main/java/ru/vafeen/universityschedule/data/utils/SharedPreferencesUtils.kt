package ru.vafeen.universityschedule.data.utils

import android.content.SharedPreferences
import com.google.gson.Gson
import ru.vafeen.universityschedule.domain.models.Settings
import ru.vafeen.universityschedule.domain.utils.SharedPreferencesValue

/**
 * Сохраняет данные в SharedPreferences или удаляет их, в зависимости от переданного блока.
 *
 * Эта функция позволяет выполнить операции редактирования SharedPreferences,
 * используя переданный блок кода.
 *
 * @param save Блок кода, который будет выполнен в контексте [SharedPreferences.Editor].
 */
internal fun SharedPreferences.saveInOrRemoveFromSharedPreferences(save: SharedPreferences.Editor.() -> Unit) {
    edit().apply {
        save() // Выполнение переданного блока.
        commit() // Применение изменений. // commit останавливает текущий поток пока не будут применены изменения
    }
}

/**
 * Получает данные из SharedPreferences с использованием переданного блока.
 *
 * @param get Блок кода, который будет выполнен в контексте [SharedPreferences].
 * @return Результат выполнения блока.
 */
internal fun <T> SharedPreferences.getFromSharedPreferences(get: SharedPreferences.() -> T): T = get()




