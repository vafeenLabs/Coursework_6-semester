package ru.vafeen.universityschedule.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vafeen.universityschedule.data.converters.DateTimeConverter
import ru.vafeen.universityschedule.data.converters.DownloadStatusConverter
import ru.vafeen.universityschedule.data.converters.FrequencyOneWayConverter
import ru.vafeen.universityschedule.data.converters.GroupDTOConverter
import ru.vafeen.universityschedule.data.converters.GroupEntityConverter
import ru.vafeen.universityschedule.data.converters.JsonStringTemplateConverter
import ru.vafeen.universityschedule.data.converters.LessonDTOConverter
import ru.vafeen.universityschedule.data.converters.LessonEntityConverter
import ru.vafeen.universityschedule.data.converters.ReleaseConverter
import ru.vafeen.universityschedule.data.converters.ReminderConverter
import ru.vafeen.universityschedule.data.converters.TeacherConverter
import ru.vafeen.universityschedule.data.converters.TimeConverter

internal val converterModule = module {
    singleOf(::DateTimeConverter)
    singleOf(::DownloadStatusConverter)
    singleOf(::FrequencyOneWayConverter)
    singleOf(::GroupDTOConverter)
    singleOf(::GroupEntityConverter)
    singleOf(::JsonStringTemplateConverter)
    singleOf(::LessonDTOConverter)
    singleOf(::LessonEntityConverter)
    singleOf(::ReleaseConverter)
    singleOf(::ReminderConverter)
    singleOf(::TeacherConverter)
    singleOf(::TimeConverter)
}



