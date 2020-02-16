package com.nekobitlz.aviasales.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City(
    @SerializedName("city") val cityName: String,
    @SerializedName("country") val countryName: String? = null,
    @SerializedName("iata") val iata: List<String> = emptyList(),
    @SerializedName("location") val location: Location
) : Serializable {
    fun getCityCode(): String = if (iata.isEmpty()) {
        cityName.take(3).trim().toUpperCase()
    } else {
        iata.first()
    }
}