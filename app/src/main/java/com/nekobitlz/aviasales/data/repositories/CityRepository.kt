package com.nekobitlz.aviasales.data.repositories

import com.nekobitlz.aviasales.data.models.Cities
import com.nekobitlz.aviasales.di.providers.ICitiesApiProvider
import io.reactivex.Single
import java.util.*

class CityRepository(private val citiesApiProvider: ICitiesApiProvider) : ICityRepository {

    override fun getCities(text: String): Single<Cities> {
        return citiesApiProvider.get().getCities(text, Locale.getDefault().language)
    }
}