package ru.vafeen.universityschedule.data.network.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Сервис для получения данных.
 */
internal interface LessonDataService {

    /**
     * Получает данные по указанному URL.
     *
     * @param link URL, откуда нужно получить данные.
     * @return [Response] с телом ответа [ResponseBody], содержащим данные таблицы.
     */
    @GET
    suspend fun getLessonData(@Url link: String): Response<ResponseBody>
}
