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
import ru.vafeen.universityschedule.data.impl.database.GroupRepositoryImpl
import ru.vafeen.universityschedule.data.impl.database.LessonRepositoryImpl
import ru.vafeen.universityschedule.data.impl.database.ReminderRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.end_points.DownloadServiceLink
import ru.vafeen.universityschedule.data.impl.network.end_points.GitHubDataServiceLink
import ru.vafeen.universityschedule.data.impl.network.repository.GroupDataRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.repository.LessonDataRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.repository.ReleaseRepositoryImpl
import ru.vafeen.universityschedule.data.impl.network.service.RefresherImpl
import ru.vafeen.universityschedule.data.impl.network.service.SettingsManagerImpl
import ru.vafeen.universityschedule.data.impl.notifications.NotificationBuilderImpl
import ru.vafeen.universityschedule.data.impl.notifications.NotificationServiceImpl
import ru.vafeen.universityschedule.data.impl.scheduler.SchedulerAPIMigrationManagerImpl
import ru.vafeen.universityschedule.data.impl.scheduler.SchedulerImpl
import ru.vafeen.universityschedule.data.network.service.DownloadService
import ru.vafeen.universityschedule.data.network.service.GitHubDataService
import ru.vafeen.universityschedule.data.network.service.server.GroupsDataService
import ru.vafeen.universityschedule.data.network.service.server.LessonDataService
import ru.vafeen.universityschedule.domain.database.GroupRepository
import ru.vafeen.universityschedule.domain.database.LessonRepository
import ru.vafeen.universityschedule.domain.database.ReminderRepository
import ru.vafeen.universityschedule.domain.network.repository.GroupDataRepository
import ru.vafeen.universityschedule.domain.network.repository.LessonDataRepository
import ru.vafeen.universityschedule.domain.network.service.Refresher
import ru.vafeen.universityschedule.domain.network.service.ReleaseRepository
import ru.vafeen.universityschedule.domain.network.service.SettingsManager
import ru.vafeen.universityschedule.domain.notifications.NotificationBuilder
import ru.vafeen.universityschedule.domain.notifications.NotificationService
import ru.vafeen.universityschedule.domain.scheduler.Scheduler
import ru.vafeen.universityschedule.domain.scheduler.SchedulerAPIMigrationManager

internal val databaseModuleImpl = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(), klass = AppDatabase::class.java, name = "universityScheduleDB.db"
        ).addMigrations(*AppDatabaseMigrationManager().migrations()).build()
    }
    singleOf(::LessonRepositoryImpl) {
        bind<LessonRepository>()
    }
    singleOf(::ReminderRepositoryImpl) {
        bind<ReminderRepository>()
    }
    singleOf(::GroupRepositoryImpl) {
        bind<GroupRepository>()
    }
}

internal val networkRepositoryModuleImpl = module {
    singleOf(::LessonDataRepositoryImpl) {
        bind<LessonDataRepository>()
    }
    singleOf(::ReleaseRepositoryImpl) {
        bind<ReleaseRepository>()
    }
    singleOf(::GroupDataRepositoryImpl) {
        bind<GroupDataRepository>()
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
    single<GitHubDataService> {
        Retrofit.Builder()
            .baseUrl(GitHubDataServiceLink.BASE_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubDataService::class.java)
    }
    single<DownloadService> {
        Retrofit.Builder()
            .baseUrl(DownloadServiceLink.BASE_LINK)
            .build().create(DownloadService::class.java)
    }
    singleOf(::LessonDataService)
    singleOf(::GroupsDataService)
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
    singleOf(::SchedulerAPIMigrationManagerImpl) {
        bind<SchedulerAPIMigrationManager>()
    }
    singleOf(::SettingsManagerImpl) {
        bind<SettingsManager>()
    }
    singleOf(::RefresherImpl) {
        bind<Refresher>()
    }
}