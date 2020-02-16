package com.nekobitlz.aviasales.data.repositories

import com.nekobitlz.aviasales.data.models.Cities
import io.reactivex.Single

interface ICityRepository {

    fun getCities(text: String): Single<Cities>
}