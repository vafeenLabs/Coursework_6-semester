package ru.vafeen.universityschedule.data.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import org.koin.java.KoinJavaComponent.getKoin
import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import java.io.IOException
import java.net.UnknownHostException

/**
 * Функция для обработки ошибок при выполнении сетевых запросов.
 * Обрабатывает исключения и возвращает результат запроса в виде [ResponseResult].
 *
 * @param response Функция, выполняющая сетевой запрос и возвращающая [ResponseResult].
 * @return Результат запроса, содержащий данные или информацию об ошибке.
 */
internal suspend fun <T> getResponseWrappedAllErrors(response: suspend () -> ResponseResult<T>): ResponseResult<T> {
    val context = getKoin().get<Context>()
    fun Context.copyTextToClipBoard(text: String?, label: String? = null) {
        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        if (text?.isNotEmpty() == true || label?.isNotEmpty() == true) clipboard.setPrimaryClip(clip)
    }
    return try {
        // Выполняем сетевой запрос
        response()
    } catch (e: UnknownHostException) {
        // Обработка ошибки отсутствия подключения к интернету
        context.copyTextToClipBoard(e.message.toString())
        ResponseResult.Error(UnknownHostException("Нет подключения к интернету"))
    } catch (e: IOException) {
        // Обработка сетевой ошибки
        context.copyTextToClipBoard(e.message.toString())
        ResponseResult.Error(IOException("Ошибка сети: ${e.localizedMessage}"))
    } catch (e: Exception) {
        // Обработка других исключений
        context.copyTextToClipBoard(e.message.toString())
        ResponseResult.Error(Exception("Неизвестная ошибка: ${e.localizedMessage}"))
    }
}