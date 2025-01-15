package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.data.database.entity.GroupEntity
import ru.vafeen.universityschedule.domain.converter.TwoWayBaseConverter
import ru.vafeen.universityschedule.domain.models.Group

/**
 * Двусторонний конвертер для преобразования между объектами [GroupEntity] и [Group].
 * Используется для конвертации данных между форматом сущности базы данных и моделью приложения.
 */
internal class GroupEntityConverter : TwoWayBaseConverter<GroupEntity, Group> {

    /**
     * Конвертирует объект [GroupEntity] в [Group].
     *
     * @param a Объект [GroupEntity] для конвертации.
     * @return Объект [Group], созданный на основе данных из [GroupEntity].
     */
    override fun convertAB(a: GroupEntity): Group {
        return Group(
            id = a.id,
            course = a.course,
            group = a.group
        )
    }

    /**
     * Конвертирует объект [Group] в [GroupEntity].
     *
     * @param b Объект [Group] для конвертации.
     * @return Объект [GroupEntity], созданный на основе данных из [Group].
     */
    override fun convertBA(b: Group): GroupEntity {
        return GroupEntity(
            id = b.id,
            course = b.course,
            group = b.group,
        )
    }
}
