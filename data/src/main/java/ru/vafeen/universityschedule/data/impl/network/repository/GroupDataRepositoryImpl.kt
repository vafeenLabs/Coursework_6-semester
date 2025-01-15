package ru.vafeen.universityschedule.data.impl.network.repository

import ru.vafeen.universityschedule.data.converters.GroupDTOConverter
import ru.vafeen.universityschedule.data.network.service.server.GroupsDataService
import ru.vafeen.universityschedule.data.utils.getResponseWrappedAllErrors
import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.network.repository.GroupDataRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

internal class GroupDataRepositoryImpl(
    private val groupsDataService: GroupsDataService,
    private val groupDTOConverter: GroupDTOConverter,
) :
    GroupDataRepository {
    /**
     * Получает список доступных групп с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список групп ([String]) или информацию об ошибке.
     */
    override suspend fun getGroups(): ResponseResult<List<Group>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(groupDTOConverter.convertList(groupsDataService.getGroups()))
        }
}