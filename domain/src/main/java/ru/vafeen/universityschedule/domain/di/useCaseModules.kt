package ru.vafeen.universityschedule.domain.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.vafeen.universityschedule.domain.usecase.CatMeowUseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.CleverUpdatingTeachersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteTeachersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.DeleteUseLessRemindersForLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetAsFlowTeachersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.GetReminderByIdOfReminderUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertGroupsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertLessonsUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertRemindersUseCase
import ru.vafeen.universityschedule.domain.usecase.db.InsertTeachersUseCase
import ru.vafeen.universityschedule.domain.usecase.network.FetchDataAndUpdateDBUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetGroupDataUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLatestReleaseUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetLessonDataUseCase
import ru.vafeen.universityschedule.domain.usecase.network.GetTeacherDataUseCase
import ru.vafeen.universityschedule.domain.usecase.notification.NotificationAbout15MinutesBeforeLessonUseCase
import ru.vafeen.universityschedule.domain.usecase.notification.NotificationAfterBeginningLessonForBeCheckedAtThisLesson
import ru.vafeen.universityschedule.domain.usecase.notification.NotificationReminderRecoveryUseCase
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
    singleOf(::GetTeacherDataUseCase)
}

internal val databaseUseCaseModule = module {
    singleOf(::CleverUpdatingGroupsUseCase)
    singleOf(::CleverUpdatingLessonsUseCase)
    singleOf(::CleverUpdatingTeachersUseCase)
    singleOf(::DeleteGroupsUseCase)
    singleOf(::DeleteLessonsUseCase)
    singleOf(::DeleteRemindersUseCase)
    singleOf(::DeleteTeachersUseCase)
    singleOf(::DeleteUseLessRemindersForLessonsUseCase)
    singleOf(::GetAsFlowGroupsUseCase)
    singleOf(::GetAsFlowLessonsUseCase)
    singleOf(::GetAsFlowRemindersUseCase)
    singleOf(::GetAsFlowTeachersUseCase)
    singleOf(::GetReminderByIdOfReminderUseCase)
    singleOf(::InsertGroupsUseCase)
    singleOf(::InsertLessonsUseCase)
    singleOf(::InsertRemindersUseCase)
    singleOf(::InsertTeachersUseCase)
}

internal val notificationUseCaseModule = module {
    singleOf(::NotificationAbout15MinutesBeforeLessonUseCase)
    singleOf(::NotificationAfterBeginningLessonForBeCheckedAtThisLesson)
    singleOf(::NotificationReminderRecoveryUseCase)
}