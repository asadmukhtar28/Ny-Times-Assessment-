package com.asad.nytimes.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.asad.nytimes.models.ArticleResponse
import com.asad.nytimes.models.base.State
import com.asad.nytimes.ui.base.BaseViewModel
import com.asad.nytimes.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(val repository: ArticlesRepository) : BaseViewModel() {

    var apiResponse: MutableSharedFlow<State<ArticleResponse>> = MutableSharedFlow()
    private val _fetchMoviesApiResponse = SingleLiveEvent<State<ArticleResponse>>()
    val fetchMoviesApiResponse: LiveData<State<ArticleResponse>> = _fetchMoviesApiResponse

    fun fetchArticles() {
        viewModelScope.launch {
            repository.fetchArticles(1).collect {
                _fetchMoviesApiResponse.value = it
            }
        }
    }
}