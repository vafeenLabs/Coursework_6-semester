package ru.vafeen.universityschedule.application_main

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.vafeen.universityschedule.noui.di.koinDIModule
import ru.vafeen.universityschedule.noui.di.koinDatabaseDIModule
import ru.vafeen.universityschedule.noui.di.koinNetworkDIModule
import ru.vafeen.universityschedule.noui.di.koinViewModelDIModule
import ru.vafeen.universityschedule.noui.notifications.NotificationChannelInfo
import ru.vafeen.universityschedule.utils.cleverUpdatingLessons
import ru.vafeen.universityschedule.utils.createGSheetsService
import ru.vafeen.universityschedule.utils.getLessonsListFromGSheetsTable
import ru.vafeen.universityschedule.utils.getSettingsOrCreateIfNull


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                koinDatabaseDIModule,
                koinNetworkDIModule,
                koinDIModule,
                koinViewModelDIModule,
            )
        }

        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            NotificationChannelInfo.NOTIFICATION_CHANNEL_ID,
            NotificationChannelInfo.NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(notificationChannel)
        val sharedPreferences: SharedPreferences by inject()
        val settings = sharedPreferences.getSettingsOrCreateIfNull()
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            settings.link?.let { link ->
                try {
                    createGSheetsService(link = link)
                        ?.getLessonsListFromGSheetsTable()
                        ?.let {
                            cleverUpdatingLessons(newLessons = it)
                        }
                } catch (e: Exception) {
                }
            }
        }
    }
}
