package com.jc.app.blog.data.utils

import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class CallServiceBase {

    abstract fun getNetworkUtils(): ConnectionUtils

    protected suspend inline fun <T> callService(
        crossinline retrofitCall: suspend () -> Response<T>
    ): Either<Failure, T> {
        return if (getNetworkUtils().isNetworkAvailable()) {
            try {
                withContext(Dispatchers.IO) {
                    val response = retrofitCall.invoke()
                    if (response.isSuccessful && response.body() != null) {
                        return@withContext Either.Success(response.body()!!)
                    } else {
                        return@withContext Either.Error(Failure.None)
                    }
                }
            } catch (e: Exception) {
                Either.Error(parseException(e))
            }
        } else {
            Either.Error(Failure.NoNetworkDetected)
        }
    }

    fun parseException(throwable: Throwable): Failure {
        return when (throwable) {
            is SocketTimeoutException -> Failure.TimeOut
            is SSLException -> Failure.NetworkConnectionLostSuddenly
            is SSLHandshakeException -> Failure.SSLError
            else -> Failure.ServiceUncaughtFailure(
                throwable.message ?: "Service response doesn't match with response object."
            )
        }
    }

}