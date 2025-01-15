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

/**
 * Класс для конвертации данных между JSON-строками и объектами Kotlin.
 * Использует Gson для сериализации и десериализации.
 *
 * @property frequencyOneWayConverter Конвертер для преобразования частоты занятий из строки.
 */
internal class JsonStringTemplateConverter(
    private val frequencyOneWayConverter: FrequencyOneWayConverter
) {
    /**
     * Экземпляр Gson, настроенный для корректной десериализации LocalTime и Frequency.
     */
    private var gson: Gson

    init {
        val gsonBuilder = GsonBuilder()

        // Регистрация десериалайзера для LocalTime
        gsonBuilder.registerTypeAdapter(LocalTime::class.java, object :
            JsonDeserializer<LocalTime> {
            /**
             * Десериализует строку в объект LocalTime.
             *
             * @param json JSON-элемент для десериализации.
             * @param typeOfT Тип объекта, который нужно получить.
             * @param context Контекст десериализации.
             * @return Объект LocalTime, полученный из строки.
             */
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalTime = LocalTime.parse(json?.asString)
        })

        // Регистрация десериалайзера для Frequency
        gsonBuilder.registerTypeAdapter(Frequency::class.java, object :
            JsonDeserializer<Frequency> {
            /**
             * Десериализует строку в объект Frequency.
             * Если строка пустая, возвращает Frequency.Every по умолчанию.
             *
             * @param json JSON-элемент для десериализации.
             * @param typeOfT Тип объекта, который нужно получить.
             * @param context Контекст десериализации.
             * @return Объект Frequency, полученный из строки.
             */
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): Frequency = json?.asString?.let { frequencyOneWayConverter.convert(it) }
                ?: Frequency.Every
        })

        gson = gsonBuilder.create()
    }

    /**
     * Конвертирует JSON-строку в объект типа T.
     *
     * @param jsonString JSON-строка для конвертации.
     * @return Объект типа T.
     */
    inline fun <reified T> convertObject(jsonString: String): T =
        gson.fromJson(jsonString, T::class.java)

    /**
     * Конвертирует JSON-строку в список объектов типа T.
     *
     * @param jsonString JSON-строка для конвертации.
     * @return Список объектов типа T.
     */
    inline fun <reified T> convertList(jsonString: String): List<T> =
        gson.fromJson(jsonString, object : TypeToken<List<T>>() {}.type)
}