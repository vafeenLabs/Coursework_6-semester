package ru.vafeen.universityschedule.data.network.service.server

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.vafeen.universityschedule.backenddto.GroupDTO
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter


internal class GroupsDataService(
    private val jsonStringTemplateConverter: JsonStringTemplateConverter,
    private val client: HttpClient,
) {
    suspend fun getGroups(): List<GroupDTO> =
        jsonStringTemplateConverter.convertList(client.get(EndPoint.groups()).body())

}