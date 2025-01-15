package ru.vafeen.universityschedule.data.utils

import ru.vafeen.universityschedule.domain.network.result.ResponseResult
import java.io.IOException
import java.net.UnknownHostException

internal suspend fun <T> getResponseWrappedAllErrors(response: suspend () -> ResponseResult<T>): ResponseResult<T> =
    response()

val x = try {
    ResponseResult.Error<Int>(Exception(""))
} catch (e: UnknownHostException) {
    // Обработка ошибки отсутствия подключения к интернету
    ResponseResult.Error(UnknownHostException("Нет подключения к интернету"))
} catch (e: IOException) {
    // Обработка сетевой ошибки
    ResponseResult.Error(IOException("Ошибка сети: ${e.localizedMessage}"))
} catch (e: Exception) {
    // Обработка других исключений
    ResponseResult.Error(Exception("Неизвестная ошибка: ${e.localizedMessage}"))
}