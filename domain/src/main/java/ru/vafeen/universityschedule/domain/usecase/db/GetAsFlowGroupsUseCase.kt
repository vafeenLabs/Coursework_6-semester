package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.domain.database.GroupRepository
import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения списка групп в виде потока.
 *
 * Этот класс предоставляет возможность получать текущий список групп из репозитория в виде
 * потока, что позволяет наблюдать за изменениями в данных.
 *
 * @property groupRepository Репозиторий, используемый для взаимодействия с данными групп.
 */
class GetAsFlowGroupsUseCase(private val groupRepository: GroupRepository) : UseCase {

    /**
     * Возвращает поток, содержащий текущий список групп.
     *
     * @return [Flow] с итерацией групп [Group].
     */
    fun invoke(): Flow<List<Group>> = groupRepository.getAsFlowGroups()
}
