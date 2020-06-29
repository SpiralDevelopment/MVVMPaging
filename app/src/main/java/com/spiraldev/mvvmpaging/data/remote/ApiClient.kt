package com.spiraldev.mvvmpaging.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.spiraldev.mvvmpaging.data.remote.Api.THE_MOVIE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun getService(): ApiService = createRetrofit().create(ApiService::class.java)

private fun createRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(THE_MOVIE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(provideOkHttpClient())
    .build()

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
