package com.nekobitlz.aviasales.di.module

import com.nekobitlz.aviasales.data.repositories.CityRepository
import com.nekobitlz.aviasales.data.repositories.ICityRepository
import com.nekobitlz.aviasales.di.providers.*

interface ApplicationComponent {
    val cityRepository: ICityRepository
}

class ApplicationModule : ApplicationComponent {

    override val cityRepository: ICityRepository = CityRepository(
        CitiesApiProvider(
            RetrofitProvider(
                OkHttpClientProvider(),
                GsonProvider()
            )
        )
    )
}