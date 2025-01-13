package ru.vafeen.universityschdeule.backenddto.converters

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

class JsonStringTemplateConverter {

    inline fun <reified T> convert(jsonString: String): T? {
        val gsonBuilder = GsonBuilder()

        // Регистрация десериалайзера для LocalTime
        gsonBuilder.registerTypeAdapter(LocalTime::class.java, object : JsonDeserializer<LocalTime> {
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

    fun <T> convert(obj: T): String? {
        val gsonBuilder = GsonBuilder()

        // Регистрация сериалайзера для LocalTime
        gsonBuilder.registerTypeAdapter(LocalTime::class.java, object : JsonSerializer<LocalTime> {
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