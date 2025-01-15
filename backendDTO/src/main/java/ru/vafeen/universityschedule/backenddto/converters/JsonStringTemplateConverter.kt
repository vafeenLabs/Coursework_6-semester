package ru.vafeen.universityschedule.backenddto.converters

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.time.LocalTime

/**
 * Класс для конвертации данных между JSON-строками и объектами Kotlin.
 * Использует Gson для сериализации и десериализации.
 */
class JsonStringTemplateConverter {

    /**
     * Конвертирует JSON-строку в объект типа T.
     * Регистрирует десериалайзер для корректного преобразования LocalTime из строки.
     *
     * @param jsonString JSON-строка для конвертации.
     * @return Объект типа T или null в случае ошибки.
     */
    inline fun <reified T> convert(jsonString: String): T? {
        val gsonBuilder = GsonBuilder()

        // Регистрация десериалайзера для LocalTime
        gsonBuilder.registerTypeAdapter(LocalTime::class.java, object : JsonDeserializer<LocalTime> {
            /**
             * Десериализует строку в объект LocalTime.
             *
             * @param json JSON-элемент для десериализации.
             * @param typeOfT Тип объекта, который нужно получить.
             * @param context Контекст десериализации.
             * @return Объект LocalTime, полученный из строки.
             */
            override fun deserialize(
                json: JsonElement,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalTime = LocalTime.parse(json.asString) // Парсим строку в LocalTime
        })
        val gson = gsonBuilder.create()

        return try {
            // Проверяем, является ли T списком
            if (T::class.java == List::class.java) {
                val type = object : TypeToken<T>() {}.type
                gson.fromJson(jsonString, type)
            } else {
                gson.fromJson(jsonString, T::class.java)
            }
        } catch (e: Exception) {
            println(e.message.toString())
            null
        }
    }

    /**
     * Конвертирует объект в JSON-строку.
     * Регистрирует сериалайзер для корректного преобразования LocalTime в строку.
     *
     * @param obj Объект для конвертации.
     * @return JSON-строка или null в случае ошибки.
     */
    fun <T> convert(obj: T): String? {
        val gsonBuilder = GsonBuilder()

        // Регистрация сериалайзера для LocalTime
        gsonBuilder.registerTypeAdapter(LocalTime::class.java, object : JsonSerializer<LocalTime> {
            /**
             * Сериализует объект LocalTime в строку.
             *
             * @param src Объект LocalTime для сериализации.
             * @param typeOfSrc Тип объекта, который сериализуется.
             * @param context Контекст сериализации.
             * @return JSON-элемент, представляющий LocalTime как строку.
             */
            override fun serialize(
                src: LocalTime,
                typeOfSrc: Type?,
                context: JsonSerializationContext?
            ): JsonElement = JsonPrimitive(src.toString()) // Преобразуем LocalTime в строку
        })
        val gson = gsonBuilder.create()

        return try {
            gson.toJson(obj)
        } catch (e: Exception) {
            println(e.message.toString())
            null
        }
    }
}
