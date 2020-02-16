package com.nekobitlz.aviasales.data.network.api

import com.nekobitlz.aviasales.data.models.Cities
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CitiesApi {

    @GET("autocomplete")
    fun getCities(
        @Query("term") text: String,
        @Query("lang") lang: String = "en"
    ): Single<Cities>
}