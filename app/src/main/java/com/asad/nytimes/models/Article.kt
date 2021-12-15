package com.asad.nytimes.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    val uri: String,
    val url: String,
    val id: String,
    @SerializedName("asset_id")
    val assetId: String,
    val source: String,
    @SerializedName("published_date")
    val publishedDate: String,
    val updated: String,
    val section: String,
    @SerializedName("subsection")
    val subSection: String,
    @SerializedName("nytdsection")
    val nytdSection: String,
    @SerializedName("adx_keywords")
    val adxKeywords: String,
    val column: String?,
    val byline: String,
    val type: String,
    val title: String,
    val abstract: String,
    val media: ArrayList<Media>
): Serializable