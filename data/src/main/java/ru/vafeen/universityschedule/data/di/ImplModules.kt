package ru.vafeen.universityschedule.data.di

import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vafeen.universityschedule.data.database.AppDatabase
import ru.vafeen.universityschedule.data.database.AppDatabaseMigrationManager
import ru.vafeen.universityschedule.data.database.DBInfo
import ru.vafeen.universityschedule.data.impl.database.GroupLocalRepositoryImpl
import ru.vafeen.universityschedule.data.impl.database.LessonLocalRepositoryImpl
import ru.vafeen.universityschedule.data.impl.database.ReminderLocalRepositoryImpl
import ru.vafeen.universityschedule.data.impl.database.TeacherLocalRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.end_points.ReleaseRemoteServiceLink
import ru.vafeen.universityschedule.data.impl.network.repository.GroupRemoteRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.repository.LessonRemoteRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.repository.ReleaseRemoteRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.repository.TeacherRemoteRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.service.RefresherImpl
import ru.vafeen.universityschedule.data.impl.network.service.SettingsManagerImpl
import ru.vafeen.universityschedule.data.impl.notifications.NotificationBuilderImpl
import ru.vafeen.universityschedule.data.impl.notifications.NotificationServiceImpl
import ru.vafeen.universityschedule.data.impl.scheduler.SchedulerImpl
import ru.vafeen.universityschedule.data.network.service.ReleaseRemoteService
import ru.vafeen.universityschedule.data.network.service.server.GroupsDataService
import ru.vafeen.universityschedule.data.network.service.server.LessonDataService
import ru.vafeen.universityschedule.data.network.service.server.TeacherDataService
import ru.vafeen.universityschedule.domain.database.GroupLocalRepository
import ru.vafeen.universityschedule.domain.database.LessonLocalRepository
import ru.vafeen.universityschedule.domain.database.ReminderLocalRepository
import ru.vafeen.universityschedule.domain.database.TeacherLocalRepository
import ru.vafeen.universityschedule.domain.network.repository.GroupRemoteRepository
import ru.vafeen.universityschedule.domain.network.repository.LessonRemoteRepository
import ru.vafeen.universityschedule.domain.network.repository.ReleaseRemoteRepository
import ru.vafeen.universityschedule.domain.network.repository.TeacherRemoteRepository
import ru.vafeen.universityschedule.domain.network.service.Refresher
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.notifications.NotificationBuilder
import ru.vafeen.universityschedule.domain.notifications.NotificationService
import ru.vafeen.universityschedule.domain.scheduler.Scheduler

internal val databaseModuleImpl = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(), klass = AppDatabase::class.java, name = DBInfo.NAME
        ).addMigrations(*AppDatabaseMigrationManager().migrations()).build()
    }
    singleOf(::LessonLocalRepositoryImpl) {
        bind<LessonLocalRepository>()
    }
    singleOf(::ReminderLocalRepositoryImpl) {
        bind<ReminderLocalRepository>()
    }
    singleOf(::GroupLocalRepositoryImpl) {
        bind<GroupLocalRepository>()
    }
    singleOf(::TeacherLocalRepositoryImpl) {
        bind<TeacherLocalRepository>()
    }
}

internal val networkRepositoryModuleImpl = module {
    singleOf(::LessonRemoteRepositoryImpl) {
        bind<LessonRemoteRepository>()
    }
    singleOf(::ReleaseRemoteRepositoryImpl) {
        bind<ReleaseRemoteRepository>()
    }
    singleOf(::GroupRemoteRepositoryImpl) {
        bind<GroupRemoteRepository>()
    }
    singleOf(::TeacherRemoteRepositoryImpl) {
        bind<TeacherRemoteRepository>()
    }
}

internal val networkServiceModuleImpl = module {
    single<HttpClient> {
        HttpClient(OkHttp) {
            // Добавляем поддержку JSON
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true // Игнорируем неизвестные поля
                    prettyPrint = true       // Для удобного форматирования
                    isLenient = true         // Лояльный режим
                })
            }

            // Настройка времени ожидания
            engine {
                config {
                    retryOnConnectionFailure(true) // Повтор подключения при сбое
                    followRedirects(true)         // Автоматическое перенаправление
                }
            }

            // Глобальные настройки клиента
            expectSuccess = true // Выброс исключений при ошибках HTTP
        }
    }
    single<ReleaseRemoteService> {
        Retrofit.Builder()
            .baseUrl(ReleaseRemoteServiceLink.BASE_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ReleaseRemoteService::class.java)
    }
    singleOf(::LessonDataService)
    singleOf(::GroupsDataService)
    singleOf(::TeacherDataService)
}

internal val servicesModuleImpl = module {
    singleOf(::NotificationServiceImpl) {
        bind<NotificationService>()
    }
    singleOf(::NotificationBuilderImpl) {
        bind<NotificationBuilder>()
    }
    singleOf(::SchedulerImpl) {
        bind<Scheduler>()
    }
    singleOf(::SettingsManagerImpl) {
        bind<SettingsManager>()
    }
    singleOf(::RefresherImpl) {
        bind<Refresher>()
    }
}