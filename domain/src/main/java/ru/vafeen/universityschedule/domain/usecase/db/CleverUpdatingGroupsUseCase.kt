package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.first
import ru.vafeen.universityschedule.domain.models.Group

class CleverUpdatingGroupsUseCase(
    private val deleteGroupsUseCase: DeleteGroupsUseCase,
    private val insertGroupsUseCase: InsertGroupsUseCase,
    private val getAsFlowGroupsUseCase: GetAsFlowGroupsUseCase,
) {

    suspend operator fun invoke(newGroups: List<Group>) {
        // Получаем текущий список групп из базы данных.
        val lastGroups = getAsFlowGroupsUseCase.invoke().first()

        // Списки для добавления новых и удаления старых групп
        val resultForInsert = mutableListOf<Group>()
        val resultForDelete = mutableListOf<Group>()

        // Проходим по новым группам и добавляем их, если они отсутствуют в базе данных.
        for (newGroup in newGroups) {
            if (!lastGroups.contains(newGroup)) {
                resultForInsert.add(newGroup)
            }
        }

        // Проходим по текущим группам и удаляем те, которые отсутствуют в новых данных.
        for (lastGroup in lastGroups) {
            if (!newGroups.contains(lastGroup)) {
                resultForDelete.add(lastGroup)
            }
        }

        // Добавляем новые группы в базу данных.
        insertGroupsUseCase.invoke(resultForInsert)

        // Удаляем старые группы из базы данных.
        deleteGroupsUseCase.invoke(resultForDelete)
    }
}
