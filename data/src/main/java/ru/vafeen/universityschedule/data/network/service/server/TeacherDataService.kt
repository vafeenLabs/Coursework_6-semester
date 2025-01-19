package ru.vafeen.universityschedule.data.network.service.server

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter

internal class TeacherDataService(
    private val jsonStringTemplateConverter: JsonStringTemplateConverter,
    private val client: HttpClient,
) {
    suspend fun getTeacherData(): List<String> = jsonStringTemplateConverter.convertList(
        client.get(EndPoint.teachers()).body()
    )
}