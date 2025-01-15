package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.GroupRepository
import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для удаления групп из базы данных.
 *
 * Этот класс отвечает за удаление одной или нескольких групп из репозитория.
 *
 * @property groupRepository Репозиторий, используемый для взаимодействия с данными групп.
 */
class DeleteGroupsUseCase(private val groupRepository: GroupRepository) : UseCase {

    /**
     * Удаляет указанные группы из базы данных.
     *
     * @param group Группы, которые нужно удалить. Можно передавать несколько групп в виде vararg.
     */
    suspend fun invoke(group: List<Group>) =
        groupRepository.deleteGroups(group)
}
