package ru.vafeen.universityschedule.domain.models.model_additions

import ru.vafeen.universityschedule.resources.R

enum class Role(val resourceName: Int) {
    Teacher(resourceName = R.string.teacher),
    Student(resourceName = R.string.student)
}