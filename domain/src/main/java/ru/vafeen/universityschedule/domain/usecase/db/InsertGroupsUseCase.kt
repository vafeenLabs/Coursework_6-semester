package ru.vafeen.universityschedule.domain.usecase.db

import ru.vafeen.universityschedule.domain.database.GroupLocalRepository
import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * UseCase для добавления групп в базу данных.
 *
 * Этот класс отвечает за вставку одной или нескольких групп в репозиторий.
 *
 * @property groupLocalRepository Репозиторий, используемый для взаимодействия с данными групп.
 */
class InsertGroupsUseCase(private val groupLocalRepository: GroupLocalRepository) : UseCase {

    /**
     * Вставляет указанные группы в базу данных.
     *
     * @param group Группы, которые нужно добавить. Можно передавать несколько групп в виде vararg.
     */
    suspend fun invoke(group: List<Group>) = groupLocalRepository.insertGroups(group)
}
