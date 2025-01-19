package ru.vafeen.universityschedule.data.impl.network.repository

import ru.vafeen.universityschedule.data.network.service.server.TeacherDataService
import ru.vafeen.universityschedule.data.utils.getResponseWrappedAllErrors
import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.network.repository.TeacherRemoteRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

internal class TeacherRemoteRepositoryImpl(private val teacherDataService: TeacherDataService) :
    TeacherRemoteRepository {
    override suspend fun getTeacherData(): ResponseResult<List<Teacher>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(teacherDataService.getTeacherData().map {
                Teacher(name = it)
            })
        }

}