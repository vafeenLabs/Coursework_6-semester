package ru.vafeen.universityschedule.backend

import ru.vafeen.universityschedule.backenddto.GroupDTO
import ru.vafeen.universityschedule.backenddto.LessonDTO
import java.time.DayOfWeek
import java.time.LocalTime



val groups = listOf(
    GroupDTO(id = GroupIds.ID61.value, course = 3, group = "ФИИТ 61"),
    GroupDTO(id = GroupIds.ID62.value, course = 3, group = "ФИИТ 62"),
    GroupDTO(id = GroupIds.ID71.value, course = 3, group = "ФИИТ 71"),
    GroupDTO(id = GroupIds.ID72.value, course = 3, group = "ФИИТ 72"),
    GroupDTO(id = GroupIds.ID3.value, course = 3, group = "ФИИТ 3"),
    GroupDTO(id = GroupIds.ID5.value, course = 3, group = "ФИИТ 5"),
    GroupDTO(id = GroupIds.ID6.value, course = 3, group = "ФИИТ 6"),
    GroupDTO(id = GroupIds.ID7.value, course = 3, group = "ФИИТ 7"),
    GroupDTO(id = GroupIds.ID8.value, course = 3, group = "ФИИТ 8")
)

val lessons3 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId =  GroupIds.ID3.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "11",
        teacher = "Крыжановская",
        groupId =  GroupIds.ID3.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId =  GroupIds.ID3.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId =  GroupIds.ID3.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "БД",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "329",
        teacher = "Есина",
        groupId =  GroupIds.ID3.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "Python",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "C#",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "226",
        teacher = "Курченкова",
        groupId =  GroupIds.ID3.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "C#",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "226",
        teacher = "Курченкова",
        groupId =  GroupIds.ID3.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ПП",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId =  GroupIds.ID3.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "КС",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "АСМ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId =  GroupIds.ID3.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId =  GroupIds.ID3.value,
        subGroup = "1",
        frequency = "Знаменатель"
    )
)

val lessons5 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID5.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID5.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID5.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID5.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID5.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID5.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "БД",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID5.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "Python",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID5.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID5.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "C#",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "226",
        teacher = "Курченкова",
        groupId = GroupIds.ID5.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ПП",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID5.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID5.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID5.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "КС",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID5.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "АСМ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID5.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID5.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID5.value,
        subGroup = "1",
        frequency = "Знаменатель"
    )
)


val lessons6 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID6.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID6.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "11",
        teacher = "Крыжановская",
        groupId = GroupIds.ID6.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID6.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КС",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID6.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID6.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID6.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID6.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "Python",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID6.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID6.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID6.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID6.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Прокопенко",
        groupId = GroupIds.ID6.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "Python",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID6.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID6.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КС",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "433",
        teacher = "Крыжановская",
        groupId = GroupIds.ID6.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID6.value,
        subGroup = "2",
        frequency = "Знаменатель"
    )
)


val lessons7 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID7.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID7.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID7.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КС",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID7.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID7.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID7.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID7.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "ПП",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "319",
        teacher = "Прокопенко",
        groupId = GroupIds.ID7.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID7.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID7.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID7.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID7.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Прокопенко",
        groupId = GroupIds.ID7.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "Python",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID7.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "12",
        teacher = "Прокопенко",
        groupId = GroupIds.ID7.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КС",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "433",
        teacher = "Крыжановская",
        groupId = GroupIds.ID7.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID7.value,
        subGroup = "2",
        frequency = "Знаменатель"
    )
)


val lessons8 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Прокопенко",
        groupId = GroupIds.ID8.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "11",
        teacher = "Крыжановская",
        groupId = GroupIds.ID8.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID8.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID8.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID8.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "Python",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID8.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "C#",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "226",
        teacher = "Курченкова",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ПП",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID8.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ЧМ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "КС",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID8.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "12",
        teacher = "Прокопенко",
        groupId = GroupIds.ID8.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID8.value,
        subGroup = "1",
        frequency = "Числитель"
    )
)

val lessons61 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "10",
        teacher = "Фролова",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "11",
        teacher = "Крыжановская",
        groupId = GroupIds.ID61.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(15, 0),
        endTime = LocalTime.of(16, 35),
        classroom = "11",
        teacher = "Крыжановская",
        groupId = GroupIds.ID61.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КС",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "11",
        teacher = "Крыжановская",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "АМУ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "9",
        teacher = "Болотова",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ДС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID61.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ДС",
        startTime = LocalTime.of(15, 0),
        endTime = LocalTime.of(16, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID61.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Прокопенко",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID61.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(15, 0),
        endTime = LocalTime.of(16, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID61.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Прокопенко",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "АСМ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID61.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "АСМ",
        startTime = LocalTime.of(15, 0),
        endTime = LocalTime.of(16, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID61.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "БД",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "КС",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ДС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID61.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ДС",
        startTime = LocalTime.of(15, 0),
        endTime = LocalTime.of(16, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID61.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "Python",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "C#",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Курченкова",
        groupId = GroupIds.ID61.value,
        subGroup = null,
        frequency = null
    )
)

val lessons62 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID62.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID62.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID62.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "319",
        teacher = "Прокопенко",
        groupId = GroupIds.ID62.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID62.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID62.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КС",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID62.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID62.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID62.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "БД",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID62.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID62.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID62.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "Python",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID62.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "C#",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Курченкова",
        groupId = GroupIds.ID62.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "Python",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID62.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Фролова",
        groupId = GroupIds.ID62.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID62.value,
        subGroup = "2",
        frequency = "Знаменатель"
    )
)

val lessons71 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID71.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "12",
        teacher = "Золотухин",
        groupId = GroupIds.ID71.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID71.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID71.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КС",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID71.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "Python",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID71.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID71.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID71.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID71.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID71.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID71.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID71.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Прокопенко",
        groupId = GroupIds.ID71.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "Python",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID71.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "12",
        teacher = "Прокопенко",
        groupId = GroupIds.ID71.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КС",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "433",
        teacher = "Крыжановская",
        groupId = GroupIds.ID71.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID71.value,
        subGroup = "1",
        frequency = "Знаменатель"
    )
)

val lessons72 = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID72.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "319",
        teacher = "Корзунина",
        groupId = GroupIds.ID72.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID72.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "ПП",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Прокопенко",
        groupId = GroupIds.ID72.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "КС",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Абрамов",
        groupId = GroupIds.ID72.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.TUESDAY,
        name = "Python",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID72.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID72.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "АСМ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID72.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID72.value,
        subGroup = "1",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "БД",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID72.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID72.value,
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "АСМ",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "20",
        teacher = "Золотухин",
        groupId = GroupIds.ID72.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "Python",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "20",
        teacher = "Вернер",
        groupId = GroupIds.ID72.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "C#",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "226",
        teacher = "Курченкова",
        groupId = GroupIds.ID72.value,
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "БД",
        startTime = LocalTime.of(13, 0),
        endTime = LocalTime.of(14, 35),
        classroom = "329",
        teacher = "Есина",
        groupId = GroupIds.ID72.value,
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "ЧМ",
        startTime = LocalTime.of(9, 0),
        endTime = LocalTime.of(10, 35),
        classroom = "12",
        teacher = "Фролова",
        groupId = GroupIds.ID72.value,
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "КГ",
        startTime = LocalTime.of(11, 0),
        endTime = LocalTime.of(12, 35),
        classroom = "20",
        teacher = "Серых",
        groupId = GroupIds.ID72.value,
        subGroup = "1",
        frequency = "Знаменатель"
    )
)






