package ru.vafeen.universityschedule.domain.usecase.network

import ru.vafeen.universityschedule.domain.network.repository.GroupDataRepository

class GetGroupDataUseCase(
    private val groupDataRepository: GroupDataRepository
) {
    suspend operator fun invoke() = groupDataRepository.getGroups()
}