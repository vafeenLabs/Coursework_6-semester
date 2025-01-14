package ru.vafeen.universityschedule.data.network.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.vafeen.universityschdeule.backenddto.LessonDTO
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter


/**
 * Сервис для получения данных.
 */
internal class LessonDataService(
    private val jsonStringTemplateConverter: JsonStringTemplateConverter
) {
    private object EndPoint {
        private const val BASE_LINK = "http://192.168.0.103:8080"
        const val GROUPS = "$BASE_LINK/groups"
        const val ALL = "$BASE_LINK/all"
        fun group(group: String): String = "$BASE_LINK/$group"
    }

    private val client = HttpClient(OkHttp) {
        // Добавляем поддержку JSON
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Игнорируем неизвестные поля
                prettyPrint = true       // Для удобного форматирования
                isLenient = true         // Лояльный режим
            })
        }

        // Настройка времени ожидания
        engine {
            config {
                retryOnConnectionFailure(true) // Повтор подключения при сбое
                followRedirects(true)         // Автоматическое перенаправление
            }
        }

        // Глобальные настройки клиента
        expectSuccess = true // Выброс исключений при ошибках HTTP
    }

    suspend fun getLessonData(): List<LessonDTO> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.ALL).body<String>())

    suspend fun getGroups(): List<String> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.GROUPS).body())

    suspend fun getLessonDataByGroup(group: String): List<LessonDTO> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.group(group)).body())
}
