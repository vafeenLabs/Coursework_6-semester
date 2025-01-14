package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.models.model_additions.Role
import ru.vafeen.universityschedule.domain.network.repository.LessonDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения данных о парах
 *
 * Этот класс отвечает за выполнение операции получения списка пар.
 *
 * @property lessonDataRepository Репозиторий, используемый для взаимодействия с данными.
 */
class GetLessonDataUseCase(
    private val lessonDataRepository: LessonDataRepository,
    private val settingsManager: SettingsManager
) : UseCase {

    /**
     * Получает список пар из таблицы Google Sheets по указанной ссылке.
     * @return [ResponseResult] с списком пар [Lesson] или ошибкой, если произошла ошибка при получении данных.
     */
    suspend fun invoke(): ResponseResult<List<Lesson>> {
        val settings = settingsManager.settingsFlow.value
        return when (settings.role) {
            Role.Student -> {
                if (settings.group != null)
                    lessonDataRepository.getLessonDataByGroup(settings.group)
                else lessonDataRepository.getAllLessonData()
            }

            else -> {
                lessonDataRepository.getAllLessonData()
            }
        }
    }
}
