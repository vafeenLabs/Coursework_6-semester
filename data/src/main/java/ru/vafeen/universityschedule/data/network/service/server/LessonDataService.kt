package ru.vafeen.universityschedule.data.network.service.server

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.vafeen.universityschedule.backenddto.LessonDTO
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter


/**
 * Сервис для получения данных.
 */
internal class LessonDataService(
    private val jsonStringTemplateConverter: JsonStringTemplateConverter,
    private val client: HttpClient,
) {

    suspend fun getLessonData(): List<LessonDTO> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.allSchedule()).body<String>())


    suspend fun getLessonDataByGroupId(groupId: Int): List<LessonDTO> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.groupSchedule(groupId)).body())

    suspend fun getLessonDataByTeacherName(teacher: String): List<LessonDTO> =
        jsonStringTemplateConverter.convertList(
            client.get(EndPoint.teacherSchedule(teacher)).body()
        )
}
