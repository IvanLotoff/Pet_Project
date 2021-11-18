package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.ReqresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .also {
            if(BuildConfig.DEBUG)
                it.addInterceptor(HttpLoggingInterceptor())
        }
        .build()

    @Singleton
    @Provides
    fun provideApi(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ReqresApi::class.java)
}

internal const val BASE_URL = "https://reqres.in/"