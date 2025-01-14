package ru.vafeen.universityschedule.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vafeen.universityschedule.domain.usecase.CatMeowUseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteUseLessRemindersForLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetReminderByIdOfReminderUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.UpdateLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.UpdateRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLatestReleaseUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLessonDataAndUpdateDBUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLessonDataUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.CancelJobUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.RebootingRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.ScheduleRepeatingJobUseCase

internal val plannerUseCaseModule = module {
    singleOf(::ScheduleRepeatingJobUseCase)
    singleOf(::CancelJobUseCase)
}

internal val networkUseCaseModule = module {
    singleOf(::GetLatestReleaseUseCase)
    singleOf(::GetLessonDataUseCase)
}

internal val databaseUseCaseModule = module {
    singleOf(::DeleteLessonsUseCase)
    singleOf(::DeleteRemindersUseCase)
    singleOf(::GetAsFlowLessonsUseCase)
    singleOf(::GetAsFlowRemindersUseCase)
    singleOf(::GetReminderByIdOfReminderUseCase)
    singleOf(::InsertLessonsUseCase)
    singleOf(::InsertRemindersUseCase)
    singleOf(::UpdateLessonsUseCase)
    singleOf(::UpdateRemindersUseCase)
    singleOf(::CleverUpdatingLessonsUseCase)
    singleOf(::GetLessonDataAndUpdateDBUseCase)
    singleOf(::DeleteUseLessRemindersForLessonsUseCase)
    singleOf(::CatMeowUseCase)
    singleOf(::RebootingRemindersUseCase)
}
