package ru.vafeen.universityschedule.backend

import ru.vafeen.universityschdeule.backenddto.LessonDTO
import java.time.DayOfWeek
import java.time.LocalTime

val lessons = listOf(
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "ЧМ",
        startTime = LocalTime.of(15, 48),
        endTime = LocalTime.of(21, 30),
        classroom = "10",
        teacher = "Фролова",
        group = "61",
        subGroup = "1",
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АСМ",
        startTime = LocalTime.of(8, 0),
        endTime = LocalTime.of(9, 35),
        classroom = "12",
        teacher = "Золотухин",
        group = "61",
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(8, 0),
        endTime = LocalTime.of(9, 35),
        classroom = "11",
        teacher = "Крыжановская",
        group = "61",
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "АМУ",
        startTime = LocalTime.of(9, 45),
        endTime = LocalTime.of(11, 20),
        classroom = "9",
        teacher = "Болотова",
        group = "61",
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(9, 45),
        endTime = LocalTime.of(11, 20),
        classroom = "11",
        teacher = "Крыжановская",
        group = "61",
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.MONDAY,
        name = "КС",
        startTime = LocalTime.of(13, 25),
        endTime = LocalTime.of(15, 0),
        classroom = "11",
        teacher = "Крыжановская",
        group = "61",
        subGroup = "1",
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.WEDNESDAY,
        name = "Тест",
        startTime = LocalTime.of(22, 15), // Ошибка в исходных данных, startTime > endTime
        endTime = LocalTime.of(21, 0),
        classroom = null,
        teacher = null,
        group = "61", // Для примера, на самом деле это должно быть исправлено
        subGroup = null,
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ПП",
        startTime = LocalTime.of(8, 0),
        endTime = LocalTime.of(9, 45),
        classroom = "319",
        teacher = "Прокопенко",
        group = "62",
        subGroup = null,
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "КС",
        startTime = LocalTime.of(8, 0),
        endTime = LocalTime.of(9, 35),
        classroom = "226",
        teacher = "Абрамов",
        group = "62",
        subGroup = null,
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 25),
        endTime = LocalTime.of(11, 50),
        classroom = "319",
        teacher = "Корзунина",
        group = "62",
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ЧМ",
        startTime = LocalTime.of(11, 30),
        endTime = LocalTime.of(13, 5),
        classroom = "319",
        teacher = "Корзунина",
        group = "62",
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "БД",
        startTime = LocalTime.of(13, 25),
        endTime = LocalTime.of(15, 0),
        classroom = "319",
        teacher = "Борисенков",
        group = "62",
        subGroup = null,
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.THURSDAY,
        name = "ПП",
        startTime = LocalTime.of(21, 55),
        endTime = LocalTime.of(22, 0),
        classroom = "дистант",
        teacher = "Яцков",
        group = "62",
        subGroup = "2",
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "АМУ",
        startTime = LocalTime.of(9, 34),
        endTime = LocalTime.of(11, 20),
        classroom = "дистант",
        teacher = "Болотова",
        group = "62",
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "АМУ",
        startTime = LocalTime.of(11, 30),
        endTime = LocalTime.of(13, 5),
        classroom = "дистант",
        teacher = "Болотова",
        group = "62",
        subGroup = "2",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "КГ",
        startTime = LocalTime.of(13, 25),
        endTime = LocalTime.of(15, 0),
        classroom = "20",
        teacher = "Серых",
        group = "62",
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "КГ",
        startTime = LocalTime.of(12, 0),
        endTime = LocalTime.of(15, 0),
        classroom = "20",
        teacher = "Серых",
        group = "62",
        subGroup = "2",
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(15, 10),
        endTime = LocalTime.of(16, 55),
        classroom = "12",
        teacher = "Прокопенко",
        group = "62",
        subGroup = "1",
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.FRIDAY,
        name = "ПП",
        startTime = LocalTime.of(16, 55),
        endTime = LocalTime.of(18, 30),
        classroom = "433",
        teacher = "Прокопенко",
        group = "62",
        subGroup = null,
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "АСМ",
        startTime = LocalTime.of(8, 0),
        endTime = LocalTime.of(9, 35),
        classroom = "20",
        teacher = "Золотухин",
        group = "62",
        subGroup = "1",
        frequency = "Числитель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "БД",
        startTime = LocalTime.of(8, 0),
        endTime = LocalTime.of(9, 35),
        classroom = "329",
        teacher = "Есина",
        group = "62",
        subGroup = null,
        frequency = "Знаменатель"
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "АСМ",
        startTime = LocalTime.of(9, 45),
        endTime = LocalTime.of(11, 20),
        classroom = "433",
        teacher = "Золотухин",
        group = "62",
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "C#",
        startTime = LocalTime.of(11, 30),
        endTime = LocalTime.of(13, 5),
        classroom = "226",
        teacher = "Курченкова",
        group = "62",
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "Python",
        startTime = LocalTime.of(13, 25),
        endTime = LocalTime.of(15, 0),
        classroom = "433",
        teacher = "Вернер",
        group = "62",
        subGroup = null,
        frequency = null
    ),
    LessonDTO(
        dayOfWeek = DayOfWeek.SATURDAY,
        name = "Python",
        startTime = LocalTime.of(15, 10),
        endTime = LocalTime.of(16, 45),
        classroom = "10",
        teacher = "Вернер",
        group = "62",
        subGroup = "1",
        frequency = null
    ),
)