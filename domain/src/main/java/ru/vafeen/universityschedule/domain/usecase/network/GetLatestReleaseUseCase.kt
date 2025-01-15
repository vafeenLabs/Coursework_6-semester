package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.models.Release
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import ru.vafeen.universityschedule.domain.network.repository.ReleaseRemoteRepository
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения последнего релиза.
 *
 * Этот класс отвечает за выполнение операции получения последнего релиза из репозитория.
 *
 * @property releaseRemoteRepository Репозиторий, используемый для взаимодействия с сетевыми запросами на получение релизов.
 */
class GetLatestReleaseUseCase(private val releaseRemoteRepository: ReleaseRemoteRepository) : UseCase {

    /**
     * Получает последний релиз.
     *
     * @return Объект [Release] последнего релиза или null, если произошла ошибка при получении данных.
     */
    suspend fun invoke(): Release? = releaseRemoteRepository.getLatestRelease().let {
        when (it) {
            is ResponseResult.Success -> it.data // Возвращаем данные в случае успеха.
            is ResponseResult.Error -> null // Возвращаем null в случае ошибки.
        }
    }
}
