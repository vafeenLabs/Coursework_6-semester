package ru.vafeen.universityschedule.data.network.service.server

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.vafeen.universityschdeule.backenddto.LessonDTO
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter


/**
 * Сервис для получения данных.
 */
internal class LessonDataService(
    private val jsonStringTemplateConverter: JsonStringTemplateConverter,
    private val client: HttpClient,
) {

    suspend fun getLessonData(): List<LessonDTO> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.ALL).body<String>())


    suspend fun getLessonDataByGroupId(groupId: Int): List<LessonDTO> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.group(groupId)).body())
}
