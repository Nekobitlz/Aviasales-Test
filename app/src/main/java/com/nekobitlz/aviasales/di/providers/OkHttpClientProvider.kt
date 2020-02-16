package com.nekobitlz.aviasales.di.providers

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface IOkHttpClientProvider {
    fun get(): OkHttpClient
}

class OkHttpClientProvider : IOkHttpClientProvider {

    override fun get(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}