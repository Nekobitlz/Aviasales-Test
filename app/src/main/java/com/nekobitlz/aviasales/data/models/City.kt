package com.nekobitlz.aviasales.data.models

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("city") val cityName: String,
    @SerializedName("country") val countryName: String? = null,
    @SerializedName("countryCode") val countryCode: String
)