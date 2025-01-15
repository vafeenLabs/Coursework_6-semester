package ru.vafeen.universityschedule.data.network.service.server

internal object EndPoint {
    private const val BASE_LINK = "http://192.168.0.103:8080"
    const val GROUPS = "$BASE_LINK/groups"
    const val ALL = "$BASE_LINK/all"
    fun group(group: Int): String = "$BASE_LINK/$group"
}