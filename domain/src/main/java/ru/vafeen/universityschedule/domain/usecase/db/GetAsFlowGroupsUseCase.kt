package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.Flow
import ru.vafeen.universityschedule.domain.database.GroupLocalRepository
import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для получения списка групп в виде потока.
 *
 * Этот класс предоставляет возможность получать текущий список групп из репозитория в виде
 * потока, что позволяет наблюдать за изменениями в данных.
 *
 * @property groupLocalRepository Репозиторий, используемый для взаимодействия с данными групп.
 */
class GetAsFlowGroupsUseCase(private val groupLocalRepository: GroupLocalRepository) : UseCase {

    /**
     * Возвращает поток, содержащий текущий список групп.
     *
     * @return [Flow] с итерацией групп [Group].
     */
    fun invoke(): Flow<List<Group>> = groupLocalRepository.getAsFlowGroups()
}
