package ru.vafeen.universityschedule.data.impl.network.repository

import ru.vafeen.universityschedule.data.converters.GroupDTOConverter
import ru.vafeen.universityschedule.data.network.service.server.GroupsDataService
import ru.vafeen.universityschedule.data.utils.getResponseWrappedAllErrors
import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.network.repository.GroupRemoteRepository
import ru.vafeen.universityschedule.domain.network.result.ResponseResult

/**
 * Реализация репозитория для работы с данными групп через сеть.
 * Использует сервис [GroupsDataService] для запросов к серверу и конвертер [GroupDTOConverter] для преобразования данных.
 *
 * @property groupsDataService Сервис для запросов к серверу.
 * @property groupDTOConverter Конвертер для преобразования данных из формата DTO в модель приложения.
 */
internal class GroupRemoteRepositoryImpl(
    private val groupsDataService: GroupsDataService,
    private val groupDTOConverter: GroupDTOConverter,
) : GroupRemoteRepository {

    /**
     * Получает список доступных групп с сервера.
     *
     * @return [ResponseResult] с результатом запроса, содержащим список групп ([Group]) или информацию об ошибке.
     */
    override suspend fun getGroups(): ResponseResult<List<Group>> =
        getResponseWrappedAllErrors {
            ResponseResult.Success(groupDTOConverter.convertList(groupsDataService.getGroups()))
        }
}
