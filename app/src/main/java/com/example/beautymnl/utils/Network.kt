package com.example.beautymnl.utils

import android.util.Log
import com.example.beautymnl.data.api.DeveloperService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class Network() {

    private val CONNECT_TIMEOUT = 10L

    lateinit var okHttpClient: OkHttpClient
    lateinit var okHttpClientWebSocket: OkHttpClient
    lateinit var retrofit: Retrofit

    lateinit var developerService: DeveloperService

    constructor(url: String) : this() {
        initOkHttp()

        retrofit = Retrofit.Builder().baseUrl(url)
            .addConverterFactory(initGsonConverter())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        developerService = retrofit.create(DeveloperService::class.java)
    }


    private fun initGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(initGson())
    }

    public fun initGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    private fun initOkHttp () {
        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor({ message -> Log.e("API", message) }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

        okHttpClientWebSocket = OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor({ message -> Log.e("WEBSOCKET", message) }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    }


}