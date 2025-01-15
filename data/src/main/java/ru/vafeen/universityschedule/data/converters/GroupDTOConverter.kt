package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.backenddto.GroupDTO
import ru.vafeen.universityschedule.domain.converter.OneWayBaseConverter
import ru.vafeen.universityschedule.domain.models.Group

/**
 * Односторонний конвертер для преобразования объекта [GroupDTO] в [Group].
 * Используется для конвертации данных из формата DTO в модель приложения.
 */
internal class GroupDTOConverter : OneWayBaseConverter<GroupDTO, Group> {

    /**
     * Конвертирует объект [GroupDTO] в [Group].
     *
     * @param a Объект [GroupDTO] для конвертации.
     * @return Объект [Group], созданный на основе данных из [GroupDTO].
     */
    override fun convert(a: GroupDTO) = Group(
        id = a.id,
        course = a.course,
        group = a.group
    )
}
