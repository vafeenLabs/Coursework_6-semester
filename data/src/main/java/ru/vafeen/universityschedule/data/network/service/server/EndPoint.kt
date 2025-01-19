package ru.vafeen.universityschedule.data.network.service.server

internal object EndPoint {
    private const val BASE_LINK = "http://192.168.0.107:8080"
    fun allSchedule() = "$BASE_LINK/all"
    fun groups(): String = "$BASE_LINK/groups"
    fun groupSchedule(group: Int): String = "$BASE_LINK/group/$group"
    fun teachers() = "$BASE_LINK/teachers"
    fun teacherSchedule(teacher: String) = "$BASE_LINK/teachers/$teacher"
}