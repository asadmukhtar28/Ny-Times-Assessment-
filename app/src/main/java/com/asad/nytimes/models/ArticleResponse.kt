package com.asad.nytimes.models

import com.asad.nytimes.models.Article
import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    val status: String,
    val copyright: String,
    @SerializedName("num_Results")
    val numResults: Int,
    @SerializedName("results")
    val articles: List<Article>

)
