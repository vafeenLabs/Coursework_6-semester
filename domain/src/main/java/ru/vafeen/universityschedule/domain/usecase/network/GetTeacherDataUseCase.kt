package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.network.repository.TeacherRemoteRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

class GetTeacherDataUseCase(private val teacherRemoteRepository: TeacherRemoteRepository) {
    suspend operator fun invoke(): ResponseResult<List<Teacher>> =
        teacherRemoteRepository.getTeacherData()
}