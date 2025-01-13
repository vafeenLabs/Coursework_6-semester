package ru.vafeen.universityschedule.data.converters

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import ru.vafeen.universityschedule.domain.models.model_additions.Frequency
import java.lang.reflect.Type
import java.time.DayOfWeek
import java.time.LocalTime

internal class JsonStringTemplateConverter(
    private val dayOfWeekConverter: DayOfWeekConverter,
    private val frequencyOneWayConverter: FrequencyOneWayConverter
) {
    inline fun <reified T> convert(jsonString: String): T? {
        val gsonBuilder = GsonBuilder()

        // Регистрация десериалайзера для LocalTime
        gsonBuilder.registerTypeAdapter(LocalTime::class.java, object :
            JsonDeserializer<LocalTime> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalTime = LocalTime.parse(json?.asString) // Парсим строку в LocalTime
        })

        // Регистрация десериалайзера для DayOfWeek
        gsonBuilder.registerTypeAdapter(DayOfWeek::class.java, object :
            JsonDeserializer<DayOfWeek> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): DayOfWeek = json?.asString?.let {
                dayOfWeekConverter.convert(it)
            } ?: DayOfWeek.SUNDAY

        })

        // Регистрация десериалайзера для Frequency
        gsonBuilder.registerTypeAdapter(Frequency::class.java, object :
            JsonDeserializer<Frequency> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): Frequency = json?.asString?.let { frequencyOneWayConverter.convert(it) }
                ?: Frequency.Every
        })

        val gson = gsonBuilder.create()
        Log.d("json", jsonString)

        try {
            // Проверяем, является ли T списком
            if (T::class.java == List::class.java) {
                val type = object : TypeToken<T>() {}.type
                gson.fromJson(jsonString, type)
            } else {
                gson.fromJson(jsonString, T::class.java)
            }.also {
                Log.d("json", it.toString())
                return it
            }
        } catch (e: Exception) {
            Log.e("json", e.message.toString())
            return null
        }
    }


}