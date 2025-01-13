package ru.vafeen.universityschedule.data.impl.network.repository

import android.util.Log
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter
import ru.vafeen.universityschedule.data.network.service.GoogleSheetsService
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.repository.SheetDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import java.io.IOException
import java.net.UnknownHostException

/**
 * Реализация репозитория для получения данных о парах из Google Sheets.
 *
 * @property googleSheetsService Сервис для выполнения запросов к Google Sheets.
 * @property lessonConverter Конвертер для преобразования объектов Lesson между слоями.
 */
internal class SheetDataRepositoryImpl(
    private val googleSheetsService: GoogleSheetsService,
    private val jsonStringTemplateConverter: JsonStringTemplateConverter,
) : SheetDataRepository {

    /**
     * Получение списка пар из таблицы Google Sheets по указанной ссылке.
     *
     * @param link Ссылка на таблицу Google Sheets.
     * @return Результат запроса, содержащий список пар или ошибку.
     */
    override suspend fun getLessonsListFromGSheetsTable(link: String): ResponseResult<List<Lesson>> =
        try {
            // Выполнение запроса
            val response = googleSheetsService.getSheetData(link)
            val body = response.body()
            // Проверяем успешность ответа и наличие тела
            if (response.isSuccessful && body != null) {
                ResponseResult.Success(
                    jsonStringTemplateConverter.convert<List<Lesson>>(body.string())
                        ?: throw Exception("Unable to convert data to lessons")
                ) // Возвращаем успешный результат
            } else {
                // Обработка HTTP ошибки, если статус не успешен
                ResponseResult.Error(Exception("Ошибка сервера: ${response.code()}"))
            }
        } catch (e: UnknownHostException) {
            // Ошибка при отсутствии интернета
            ResponseResult.Error(UnknownHostException("Нет подключения к интернету"))
        } catch (e: IOException) {
            // Общая ошибка сети
            ResponseResult.Error(IOException("Ошибка сети: ${e.localizedMessage}"))
        } catch (e: Exception) {
            // Обработка любых других ошибок
            ResponseResult.Error(Exception("Неизвестная ошибка: ${e.localizedMessage}"))
        }
}