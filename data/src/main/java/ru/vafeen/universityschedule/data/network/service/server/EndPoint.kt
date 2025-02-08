package ru.vafeen.universityschedule.data.network.service.server

internal object EndPoint {
    private const val BASE_LINK = "http://10.0.2.2:8080"
    fun allSchedule() = "$BASE_LINK/all"
    fun groups(): String = "$BASE_LINK/groups"
    fun groupSchedule(group: Int): String = "$BASE_LINK/group/$group"
    fun teachers() = "$BASE_LINK/teachers"
    fun teacherSchedule(teacher: String) = "$BASE_LINK/teachers/$teacher"
}