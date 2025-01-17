package ru.vafeen.universityschedule.data.network.service

import retrofit2.Response
import retrofit2.http.GET
import ru.vafeen.universityschedule.data.impl.network.end_points.ReleaseRemoteServiceLink
import ru.vafeen.universityschedule.data.network.dto.github_service.ReleaseDTO

/**
 * Интерфейс для определения методов API, связанных с получением данных о релизах на GitHub.
 */
internal interface ReleaseRemoteService {

    /**
     * Получает информацию о последнем релизе.
     *
     * @return [Response] с объектом [ReleaseDTO], содержащим данные о последнем релизе.
     */
    @GET(ReleaseRemoteServiceLink.EndPoint.LATEST_RELEASE_INFO)
    suspend fun getLatestRelease(): Response<ReleaseDTO>
}
