package ru.vafeen.universityschedule.app

import android.app.Application
import android.app.NotificationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ru.vafeen.universityschedule.data.di.main.mainDataModule
import ru.vafeen.universityschedule.domain.di.main.mainDomainModule
import ru.vafeen.universityschedule.domain.notifications.NotificationChannelInfo
import ru.vafeen.universityschedule.domain.usecase.network.GetSheetDataAndUpdateDBUseCase
import ru.vafeen.universityschedule.domain.utils.createNotificationChannelKClass
import ru.vafeen.universityschedule.presentation.di.main.mainPresentationModule
import ru.vafeen.universityschedule.presentation.utils.Link

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                mainPresentationModule,
                mainDomainModule,
                mainDataModule,
            )
        }
        val getSheetDataAndUpdateDBUseCase = get<GetSheetDataAndUpdateDBUseCase>()
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            getSheetDataAndUpdateDBUseCase.invoke(Link.LINK_DATA)
        }
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(
            NotificationChannelInfo.About15MinutesBeforeLesson.createNotificationChannelKClass()
        )
        notificationManager.createNotificationChannel(
            NotificationChannelInfo.AfterStartingLesson.createNotificationChannelKClass()
        )
        notificationManager.createNotificationChannel(
            NotificationChannelInfo.ReminderRecovery.createNotificationChannelKClass()
        )
    }
}
