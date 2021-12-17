package com.asad.nytimes.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asad.nytimes.models.base.State

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData(false)
    val error = MutableLiveData<State.ResponseError>()
}