package com.nekobitlz.aviasales.di.providers

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface IRetrofitProvider {
    fun get(): Retrofit
}

class RetrofitProvider(
    private val clientProvider: IOkHttpClientProvider,
    private val gsonProvider: IGsonProvider
) : IRetrofitProvider {

    private val API_URL = "https://yasen.hotellook.com/"

    override fun get(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(API_URL)
            .client(clientProvider.get())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonProvider.get()))
            .build()
    }
}