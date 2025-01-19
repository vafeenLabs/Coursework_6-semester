package ru.vafeen.universityschedule.domain.network.repository

import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

interface TeacherRemoteRepository {
    suspend fun getTeacherData(): ResponseResult<List<Teacher>>
}