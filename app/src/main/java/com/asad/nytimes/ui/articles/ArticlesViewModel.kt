package com.asad.nytimes.ui.articles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.asad.nytimes.models.Article
import com.asad.nytimes.models.base.State
import com.asad.nytimes.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(val repository: ArticlesRepository) : BaseViewModel() {

    val articles = MutableLiveData<List<Article>>()

    fun fetchArticles() {
        viewModelScope.launch {
            repository.fetchArticles(1).collect {
                when (it) {
                    is State.Loading -> {
                        isLoading.value = true
                    }
                    is State.Success -> {
                        isLoading.value = false
                        it.wrapperData.articles.let { list ->
                            if (list.isNotEmpty()) {
                                articles.value = list
                            }
                        }
                    }
                    is State.Error -> {
                        isLoading.value = false
                        error.value = it.responseResponseError
                    }
                }
            }
        }
    }
}