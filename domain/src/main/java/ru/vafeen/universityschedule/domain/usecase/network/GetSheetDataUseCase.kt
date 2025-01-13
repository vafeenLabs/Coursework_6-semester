package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.repository.SheetDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения данных о парах
 *
 * Этот класс отвечает за выполнение операции получения списка пар.
 *
 * @property sheetDataRepository Репозиторий, используемый для взаимодействия с данными.
 */
class GetSheetDataUseCase(private val sheetDataRepository: SheetDataRepository) : UseCase {

    /**
     * Получает список пар из таблицы Google Sheets по указанной ссылке.
     *
     * @param link Ссылка на таблицу Google Sheets, откуда нужно получить данные.
     * @return [ResponseResult] с списком пар [Lesson] или ошибкой, если произошла ошибка при получении данных.
     */
    suspend fun invoke(link: String): ResponseResult<List<Lesson>> =
        sheetDataRepository.getLessonsListFromGSheetsTable(link)
}
