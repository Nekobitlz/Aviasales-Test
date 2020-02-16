package com.nekobitlz.aviasales.di.providers

import com.nekobitlz.aviasales.data.network.api.CitiesApi

interface ICitiesApiProvider {
    fun get(): CitiesApi
}

class CitiesApiProvider(private val retrofitProvider: IRetrofitProvider) : ICitiesApiProvider {

    override fun get(): CitiesApi {
        return retrofitProvider.get().create(CitiesApi::class.java)
    }

}