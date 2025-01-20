package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.models.Teacher
import ru.vafeen.universityschedule.domain.network.repository.TeacherRemoteRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * Юзкейс для получения данных о преподавателях из удаленного репозитория.
 *
 * Этот класс инкапсулирует логику получения данных о преподавателях с сервера
 * через [TeacherRemoteRepository].
 *
 * @property teacherRemoteRepository Репозиторий для получения данных о преподавателях.
 */
class GetTeacherDataUseCase(private val teacherRemoteRepository: TeacherRemoteRepository) : UseCase {

    /**
     * Выполняет запрос на получение данных о преподавателях.
     *
     * Этот метод асинхронно запрашивает данные о преподавателях из удаленного репозитория
     * и возвращает результат в виде [ResponseResult]. В случае успешного запроса,
     * возвращается список объектов [Teacher]. В случае ошибки возвращается объект [ResponseResult.Error].
     *
     * @return [ResponseResult<List<Teacher>>] Результат выполнения запроса.
     */
    suspend operator fun invoke(): ResponseResult<List<Teacher>> =
        teacherRemoteRepository.getTeacherData()
}