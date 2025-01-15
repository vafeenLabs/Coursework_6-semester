package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.models.model_additions.Role
import ru.vafeen.universityschedule.domain.network.repository.LessonDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * Юзкейс для получения данных о парах.
 * Отвечает за выполнение операции получения списка пар из репозитория.
 *
 * @property lessonDataRepository Репозиторий, используемый для взаимодействия с данными о парах.
 * @property settingsManager Менеджер настроек, предоставляющий информацию о текущих настройках приложения.
 */
class GetLessonDataUseCase(
    private val lessonDataRepository: LessonDataRepository,
    private val settingsManager: SettingsManager
) : UseCase {

    /**
     * Получает список пар из таблицы Google Sheets.
     * Если пользователь является студентом и указана группа, возвращает расписание для этой группы.
     * В противном случае возвращает полный список всех пар.
     *
     * @return [ResponseResult] с списком пар [Lesson] или ошибкой, если произошла ошибка при получении данных.
     */
    suspend fun invoke(): ResponseResult<List<Lesson>> {
        val settings = settingsManager.settingsFlow.value
        return when (settings.role) {
            Role.Student -> {
                if (settings.groupId != null)
                    lessonDataRepository.getLessonDataByGroupId(settings.groupId)
                else lessonDataRepository.getAllLessonData()
            }

            else -> {
                lessonDataRepository.getAllLessonData()
            }
        }
    }
}
