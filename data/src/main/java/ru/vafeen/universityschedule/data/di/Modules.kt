package ru.vafeen.universityschedule.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vafeen.universityschedule.data.converters.DateTimeConverter
import ru.vafeen.universityschedule.data.converters.DownloadStatusConverter
import ru.vafeen.universityschedule.data.converters.FrequencyOneWayConverter
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter
import ru.vafeen.universityschedule.data.converters.LessonDTOConverter
import ru.vafeen.universityschedule.data.converters.LessonEntityConverter
import ru.vafeen.universityschedule.data.converters.ReleaseConverter
import ru.vafeen.universityschedule.data.converters.ReminderConverter
import ru.vafeen.universityschedule.data.converters.TimeConverter

internal val converterModule = module {
    singleOf(::DateTimeConverter)
    singleOf(::DownloadStatusConverter)
    singleOf(::LessonEntityConverter)
    singleOf(::LessonDTOConverter)
    singleOf(::ReleaseConverter)
    singleOf(::ReminderConverter)
    singleOf(::TimeConverter)
    singleOf(::FrequencyOneWayConverter)
    singleOf(::JsonStringTemplateConverter)
}



