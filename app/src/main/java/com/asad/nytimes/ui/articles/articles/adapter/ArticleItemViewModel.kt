package com.asad.nytimes.ui.articles.articles.adapter

import androidx.lifecycle.MutableLiveData
import com.asad.nytimes.models.Article

class ArticleItemViewModel(article: Article) {
    val articleTitle = MutableLiveData(article.title)
    val articleAbstract = MutableLiveData(article.abstract)
    val articleThumb = MutableLiveData(article.media[0].mediaMetaData[0].url)
}