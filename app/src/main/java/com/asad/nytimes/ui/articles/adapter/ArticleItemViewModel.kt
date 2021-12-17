package com.asad.nytimes.ui.articles.adapter

import androidx.lifecycle.MutableLiveData
import com.asad.nytimes.interfaces.OnItemClickListener
import com.asad.nytimes.models.Article

class ArticleItemViewModel(article: Article, val listener : OnItemClickListener) {
    val articleTitle = MutableLiveData(article.title)
    val articleAbstract = MutableLiveData(article.abstract)
    val articleThumb = MutableLiveData<String>("")
    val articleSource = MutableLiveData<String>(article.source)
    val publishedDate = MutableLiveData<String>(article.publishedDate)

    init {
        if (article.media.isNotEmpty() && article.media[0].mediaMetaData.isNotEmpty()) {
            articleThumb.value = article.media[0].mediaMetaData[0].url
        }
    }

    fun onItemClick(){
        listener.onItemClick()
    }
}