package ru.vafeen.universityschedule.domain.notifications

import android.app.Notification

/**
 * Интерфейс для построения уведомлений.
 */
interface NotificationBuilder {

    /**
     * Создает уведомление за 15 минут до начала пары.
     *
     * @param title Заголовок уведомления (по умолчанию "title").
     * @param text Текст уведомления (по умолчанию "Hello world!").
     * @return Объект [Notification], представляющий созданное уведомление.
     */
    fun createNotificationAbout15MinutesBeforeLesson(
        title: String = "title",
        text: String = "Hello world!",
    ): Notification

    /**
     * Создает уведомление после начала пары, которое нужно проверить на этой паре.
     *
     * @param title Заголовок уведомления (по умолчанию "title").
     * @param text Текст уведомления (по умолчанию "Hello world!").
     * @return Объект [Notification], представляющий созданное уведомление.
     */
    fun createNotificationAfterBeginningLessonForBeCheckedAtThisLesson(
        title: String = "title",
        text: String = "Hello world!",
    ): Notification

    /**
     * Создает уведомление для восстановления напоминания.
     *
     * @param title Заголовок уведомления (по умолчанию "title").
     * @param text Текст уведомления (по умолчанию "Hello world!").
     * @return Объект [Notification], представляющий созданное уведомление.
     */
    fun createNotificationReminderRecovery(
        title: String = "title",
        text: String = "Hello world!",
    ): Notification
}
