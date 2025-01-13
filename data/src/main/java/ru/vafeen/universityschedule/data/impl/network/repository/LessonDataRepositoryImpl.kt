package ru.vafeen.universityschedule.data.impl.network.repository

import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter
import ru.vafeen.universityschedule.data.network.service.LessonDataService
import ru.vafeen.universityschedule.domain.models.Lesson
import ru.vafeen.universityschedule.domain.network.repository.LessonDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import java.io.IOException
import java.net.UnknownHostException

/**
 * Реализация репозитория для получения данных о расписании занятий.
 *
 * @property lessonDataService Сервис для выполнения запросов для получения данных.
 * @property jsonStringTemplateConverter Конвертер для преобразования JSON-строк в объекты Lesson.
 */
internal class LessonDataRepositoryImpl(
    private val lessonDataService: LessonDataService,
    private val jsonStringTemplateConverter: JsonStringTemplateConverter,
) : LessonDataRepository {

    /**
     * Получение списка занятий по указанной ссылке.
     *
     * @param link Ссылка на данные.
     * @return [ResponseResult] содержащий список занятий или информацию об ошибке.
     */
    override suspend fun getLessonsListFromGSheetsTable(link: String): ResponseResult<List<Lesson>> =
        try {
            // Выполнение запроса к сервису
            val response = lessonDataService.getLessonData(link)
            val body = response.body()

            // Проверяем успешность ответа и наличие тела
            if (response.isSuccessful && body != null) {
                ResponseResult.Success(
                    jsonStringTemplateConverter.convert<List<Lesson>>(body.string())
                        ?: throw Exception("Не удалось преобразовать данные в список занятий")
                )
            } else {
                // Обработка ошибки при неуспешном HTTP-ответе
                ResponseResult.Error(Exception("Ошибка сервера: ${response.code()}"))
            }
        } catch (e: UnknownHostException) {
            // Обработка ошибки отсутствия подключения к интернету
            ResponseResult.Error(UnknownHostException("Нет подключения к интернету"))
        } catch (e: IOException) {
            // Обработка сетевой ошибки
            ResponseResult.Error(IOException("Ошибка сети: ${e.localizedMessage}"))
        } catch (e: Exception) {
            // Обработка других исключений
            ResponseResult.Error(Exception("Неизвестная ошибка: ${e.localizedMessage}"))
        }
}