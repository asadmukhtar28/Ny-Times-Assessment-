package com.asad.nytimes.ui.base

import android.app.Application
import com.asad.nytimes.models.base.State
import com.asad.nytimes.utils.NetworkUtils
import retrofit2.Response
import javax.inject.Inject

open class BaseRepository @Inject constructor() {

    @Inject
    lateinit var context: Application

    suspend fun <T : Any> makeApiCall(
        apiCall: suspend () -> Response<T>,
    ): State<T> {
        return verifySessionAndMakeApiCall(apiCall, false)
    }

    private fun isNetworkNotAvailable(): Boolean {
        var isConnected = true
        if (!NetworkUtils.isNetworkConnected(context)) {
            isConnected = false
        }
        return !isConnected
    }

    private suspend fun <T : Any> verifySessionAndMakeApiCall(
        call: suspend () -> Response<T>,
        verifySession: Boolean = true,
    ): State<T> {
        if (isNetworkNotAvailable()) {
            return State.Error(State.ResponseError.InternetConnectionResponseError)
        }

        return apiOutput(call)

    }

    private suspend fun <T : Any> apiOutput(
        call: suspend () -> Response<T>,
    ): State<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            State.Success(response.body()!!)
        else {
            State.Error(State.ResponseError.SomethingWentWrong())
        }
    }
}