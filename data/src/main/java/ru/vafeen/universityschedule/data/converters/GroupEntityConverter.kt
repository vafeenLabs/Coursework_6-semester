package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.data.database.entity.GroupEntity
import ru.vafeen.universityschedule.domain.converter.TwoWayBaseConverter
import ru.vafeen.universityschedule.domain.models.Group

internal class GroupEntityConverter : TwoWayBaseConverter<GroupEntity, Group> {

    /**
     * Конвертирует объект [GroupEntity] в [Group].
     *
     * @param a Объект [GroupEntity] для конвертации.
     * @return Объект [Group].
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
     * @return Объект [GroupEntity].
     */
    override fun convertBA(b: Group): GroupEntity {
        return GroupEntity(
            id = b.id,
            course = b.course,
            group = b.group,
        )
    }
}
