package com.asad.nytimes.data.remote

import com.asad.nytimes.BuildConfig
import com.asad.nytimes.models.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleService {
    @GET("mostviewed/all-sections/{page}")
    suspend fun getArticles(
        @Path("page") page: String,
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): Response<ArticleResponse>
}