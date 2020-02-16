package com.nekobitlz.aviasales.data.repositories

import com.nekobitlz.aviasales.data.models.Cities
import com.nekobitlz.aviasales.di.providers.ICitiesApiProvider
import io.reactivex.Single

class CityRepository(private val citiesApiProvider: ICitiesApiProvider) : ICityRepository {

    override fun getCities(text: String): Single<Cities> {
        return citiesApiProvider.get().getCities(text)
    }
}