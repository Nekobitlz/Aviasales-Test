package com.nekobitlz.aviasales.di.module

import android.content.Context
import com.nekobitlz.aviasales.data.repositories.CityRepository
import com.nekobitlz.aviasales.data.repositories.ICityRepository
import com.nekobitlz.aviasales.di.providers.*

interface ApplicationComponent {
    val cityRepository: ICityRepository
    val resourceProvider: IResourceProvider
}

class ApplicationModule(context: Context) : ApplicationComponent {

    override val cityRepository: ICityRepository = CityRepository(
        CitiesApiProvider(
            RetrofitProvider(
                OkHttpClientProvider(),
                GsonProvider()
            )
        )
    )
    override var resourceProvider: IResourceProvider = ResourceProvider(context)
}