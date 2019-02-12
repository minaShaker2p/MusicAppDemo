package com.task.mina.musicapp.data.remote.network.exception

import com.task.mina.musicapp.base.domain.exception.MusicAppException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

object NetworkException {
    fun httpError(response: Response<Any>?): MusicAppException {
        var message: String? = null
        var responseBody: String? = null
        var statusCode = 0
        var errorCode = 0
        response?.let { statusCode = it.code() }
        response?.let {
            responseBody = it.errorBody()?.string()
            try {
                // in case of handle http API error
            } catch (exception: Exception) {
            }
        }

        var kind = MusicAppException.Kind.HTTP
        when (statusCode) {
            500 -> kind = MusicAppException.Kind.SERVER_DOWN
            408 -> kind = MusicAppException.Kind.TIME_OUT
            401 -> kind = MusicAppException.Kind.UNAUTHORIZED
        }

        return MusicAppException(kind, message?.let { message }
                ?: kotlin.run { "" })
                .setErrorCode(errorCode)
                .setStatusCode(statusCode)
                .setData(responseBody)
    }

    fun networkError(exception: IOException): MusicAppException {
        return MusicAppException(MusicAppException.Kind.NETWORK, exception)
    }

    fun unexpectedError(exception: Throwable): MusicAppException {
        return MusicAppException(MusicAppException.Kind.UNEXPECTED, exception)
    }


}