package ru.vafeen.universityschedule.domain.di.main

import org.koin.dsl.module
import ru.vafeen.universityschedule.domain.di.databaseUseCaseModule
import ru.vafeen.universityschedule.domain.di.networkUseCaseModule
import ru.vafeen.universityschedule.domain.di.notificationUseCaseModule
import ru.vafeen.universityschedule.domain.di.plannerUseCaseModule
import ru.vafeen.universityschedule.domain.di.useCaseModule

val mainDomainModule = module {
    includes(
        useCaseModule,
        plannerUseCaseModule,
        networkUseCaseModule,
        databaseUseCaseModule,
        notificationUseCaseModule
    )
}