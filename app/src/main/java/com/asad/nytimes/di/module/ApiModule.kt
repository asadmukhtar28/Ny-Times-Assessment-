package com.asad.nytimes.di.module

import com.asad.nytimes.BuildConfig
import com.asad.nytimes.data.remote.ApiServices
import com.asad.nytimes.utils.ApiHttpClient
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): ApiServices =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(ApiHttpClient.getHTTPClient())
            .build()
            .create(ApiServices::class.java)


}
