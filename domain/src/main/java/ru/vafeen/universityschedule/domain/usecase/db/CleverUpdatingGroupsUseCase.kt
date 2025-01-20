package ru.vafeen.universityschedule.domain.usecase.db

import kotlinx.coroutines.flow.first
import ru.vafeen.universityschedule.domain.models.Group
import ru.vafeen.universityschedule.domain.usecase.base.UseCase

/**
 * Юзкейс для умного обновления групп в базе данных.
 *
 * Этот класс отвечает за синхронизацию групп в локальной базе данных с новыми данными,
 * полученными из удаленного источника. Он удаляет старые группы и добавляет новые,
 * если они отсутствуют в базе данных.
 *
 * @property deleteGroupsUseCase Юзкейс для удаления групп из базы данных.
 * @property insertGroupsUseCase Юзкейс для вставки групп в базу данных.
 * @property getAsFlowGroupsUseCase Юзкейс для получения текущих групп в виде потока.
 */
class CleverUpdatingGroupsUseCase(
    private val deleteGroupsUseCase: DeleteGroupsUseCase,
    private val insertGroupsUseCase: InsertGroupsUseCase,
    private val getAsFlowGroupsUseCase: GetAsFlowGroupsUseCase,
) : UseCase {

    /**
     * Обновляет группы в базе данных на основе новых данных.
     *
     * Этот метод выполняет следующие шаги:
     * 1. Получает текущий список групп из базы данных.
     * 2. Сравнивает текущие группы с новыми данными.
     * 3. Добавляет группы, которые отсутствуют в базе данных.
     * 4. Удаляет группы, которые не присутствуют в новых данных.
     *
     * @param newGroups Список новых групп, полученных из удаленного источника.
     */
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
