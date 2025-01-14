package ru.vafeen.universityschedule.data.converters

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import ru.vafeen.universityschedule.domain.models.model_additions.Frequency
import java.lang.reflect.Type
import java.time.LocalTime

internal class JsonStringTemplateConverter(
    private val frequencyOneWayConverter: FrequencyOneWayConverter
) {
    private var gson: Gson

    init {
        val gsonBuilder = GsonBuilder()

        // Регистрация десериалайзера для LocalTime
        gsonBuilder.registerTypeAdapter(LocalTime::class.java, object :
            JsonDeserializer<LocalTime> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalTime = LocalTime.parse(json?.asString)
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

        gson = gsonBuilder.create()
    }

    inline fun <reified T> convertObject(jsonString: String): T =
        gson.fromJson(jsonString, T::class.java)

    inline fun <reified T> convertList(jsonString: String): List<T> =
        gson.fromJson(jsonString, object : TypeToken<List<T>>() {}.type)

}