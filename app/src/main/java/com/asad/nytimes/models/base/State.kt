package com.asad.nytimes.models.base

/**
 * State Management for UI & Data.
 */
sealed class State<out T> {

    data class Success<T>(val wrapperData: T) : State<T>()

    data class Error(val responseResponseError: ResponseError) : State<Nothing>()

    data class Loading(val wrapperData: Nothing? = null) : State<Nothing>()

    sealed class ResponseError(val message: String = "", statusCode: Int? = null) {
        object InternetConnectionResponseError : ResponseError()
        data class SomethingWentWrong(val errorMessage: String = "") : ResponseError(errorMessage)
    }

}
