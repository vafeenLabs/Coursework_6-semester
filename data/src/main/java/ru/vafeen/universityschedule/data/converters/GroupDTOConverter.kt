package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschdeule.backenddto.GroupDTO
import ru.vafeen.universityschedule.domain.converter.OneWayBaseConverter
import ru.vafeen.universityschedule.domain.models.Group

internal class GroupDTOConverter : OneWayBaseConverter<GroupDTO, Group> {

    /**
     * Конвертирует объект [GroupDTO] в [Group].
     *
     * @param a Объект [GroupDTO] для конвертации.
     * @return Объект [Group].
     */


    override fun convert(a: GroupDTO) = Group(
        id = a.id,
        course = a.course,
        group = a.group
    )
}
