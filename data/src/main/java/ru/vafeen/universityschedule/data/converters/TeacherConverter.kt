package ru.vafeen.universityschedule.data.converters

import ru.vafeen.universityschedule.data.database.entity.TeacherEntity
import ru.vafeen.universityschedule.domain.converter.TwoWayBaseConverter
import ru.vafeen.universityschedule.domain.models.Teacher

internal class TeacherConverter : TwoWayBaseConverter<TeacherEntity, Teacher> {
    override fun convertAB(a: TeacherEntity): Teacher = Teacher(name = a.name)

    override fun convertBA(b: Teacher): TeacherEntity = TeacherEntity(name = b.name)

}