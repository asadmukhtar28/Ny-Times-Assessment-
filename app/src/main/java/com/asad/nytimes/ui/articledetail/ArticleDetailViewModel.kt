package com.asad.nytimes.ui.articledetail

import androidx.lifecycle.MutableLiveData
import com.asad.nytimes.models.Article
import com.asad.nytimes.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor() : BaseViewModel() {

    val articleTitle = MutableLiveData<String>()
    val articleAbstract = MutableLiveData<String>()
    val articleThumb = MutableLiveData<String>("")
    val articleSource = MutableLiveData<String>()
    val publishedDate = MutableLiveData<String>()

    fun setArticleData(article: Article) {
        articleTitle.value = article.title
        articleAbstract.value = article.abstract
        articleSource.value = article.source
        publishedDate.value = article.publishedDate

        if (article.media.isNotEmpty() && article.media[0].mediaMetaData.isNotEmpty()) {
            articleThumb.value = article.media[0].mediaMetaData[0].url

        }
    }
}

