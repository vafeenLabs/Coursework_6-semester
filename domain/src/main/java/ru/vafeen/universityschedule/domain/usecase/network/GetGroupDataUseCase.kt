package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.network.repository.GroupDataRepository

/**
 * Юзкейс для получения данных о группах с сервера.
 * Использует репозиторий [GroupDataRepository] для запроса данных.
 *
 * @property groupDataRepository Репозиторий для работы с данными групп.
 */
class GetGroupDataUseCase(
    private val groupDataRepository: GroupDataRepository
) {
    /**
     * Получает список всех доступных групп с сервера.
     *
     * @return Результат запроса, содержащий список групп или информацию об ошибке.
     */
    suspend operator fun invoke() = groupDataRepository.getGroups()
}
