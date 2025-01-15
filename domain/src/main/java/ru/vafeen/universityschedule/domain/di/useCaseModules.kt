package ru.vafeen.universityschedule.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vafeen.universityschedule.domain.usecase.CatMeowUseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteUseLessRemindersForLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetReminderByIdOfReminderUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.network.FetchDataAndUpdateDBUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetGroupDataUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLatestReleaseUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLessonDataUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.CancelJobUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.RebootingRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.scheduler.ScheduleRepeatingJobUseCase

internal val useCaseModule = module {
    singleOf(::CatMeowUseCase)
}

internal val plannerUseCaseModule = module {
    singleOf(::ScheduleRepeatingJobUseCase)
    singleOf(::CancelJobUseCase)

    singleOf(::RebootingRemindersUseCase)
}

internal val networkUseCaseModule = module {
    singleOf(::FetchDataAndUpdateDBUseCase)
    singleOf(::GetLatestReleaseUseCase)
    singleOf(::GetGroupDataUseCase)
    singleOf(::GetLessonDataUseCase)
}

internal val databaseUseCaseModule = module {
    singleOf(::CleverUpdatingLessonsUseCase)
    singleOf(::DeleteGroupsUseCase)
    singleOf(::DeleteLessonsUseCase)
    singleOf(::DeleteRemindersUseCase)
    singleOf(::DeleteUseLessRemindersForLessonsUseCase)
    singleOf(::GetAsFlowGroupsUseCase)
    singleOf(::GetAsFlowLessonsUseCase)
    singleOf(::GetAsFlowRemindersUseCase)
    singleOf(::GetReminderByIdOfReminderUseCase)
    singleOf(::InsertGroupsUseCase)
    singleOf(::InsertLessonsUseCase)
    singleOf(::InsertRemindersUseCase)
}
