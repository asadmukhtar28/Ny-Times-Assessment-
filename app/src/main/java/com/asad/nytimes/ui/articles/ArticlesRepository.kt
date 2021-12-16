package com.asad.nytimes.ui.articles

import com.asad.nytimes.data.remote.ApiServices
import com.asad.nytimes.models.ArticleResponse
import com.asad.nytimes.models.base.State
import com.asad.nytimes.ui.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArticlesRepository @Inject constructor(val apiServices: ApiServices) : BaseRepository() {

    suspend fun fetchArticles(pageId: Int): Flow<State<ArticleResponse>> {
        return flow {
            emit(State.Loading())
            val result = makeApiCall { apiServices.getArticles("$pageId.json") }
            emit(result)
        }.flowOn(Dispatchers.IO).catch {
            emit(State.Error(State.ResponseError.SomethingWentWrong()))
        }
    }
}