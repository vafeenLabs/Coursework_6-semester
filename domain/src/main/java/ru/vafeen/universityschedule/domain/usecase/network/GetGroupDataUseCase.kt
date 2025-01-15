package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.network.repository.GroupRemoteRepository

/**
 * Юзкейс для получения данных о группах с сервера.
 * Использует репозиторий [GroupRemoteRepository] для запроса данных.
 *
 * @property groupRemoteRepository Репозиторий для работы с данными групп.
 */
class GetGroupDataUseCase(
    private val groupRemoteRepository: GroupRemoteRepository
) {
    /**
     * Получает список всех доступных групп с сервера.
     *
     * @return Результат запроса, содержащий список групп или информацию об ошибке.
     */
    suspend operator fun invoke() = groupRemoteRepository.getGroups()
}
