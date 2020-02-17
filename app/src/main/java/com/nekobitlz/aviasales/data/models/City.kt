package com.nekobitlz.aviasales.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City(
    @SerializedName("city") val cityName: String,
    @SerializedName("country") val countryName: String? = null,
    @SerializedName("countryCode") val countryCode: String,
    @SerializedName("location") val location: Location? = null
) : Serializable