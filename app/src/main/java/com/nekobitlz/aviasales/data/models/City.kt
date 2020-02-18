package com.nekobitlz.aviasales.data.models

import com.google.gson.annotations.SerializedName
import com.nekobitlz.aviasales.utils.StringUtils
import java.io.Serializable
import java.util.*

data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("city") val cityName: String,
    @SerializedName("country") val countryName: String? = null,
    @SerializedName("iata") val iata: List<String> = emptyList(),
    @SerializedName("location") val location: Location
) : Serializable {
    fun getCityCode(): String = when {
        iata.isEmpty() && Locale.getDefault().language == "ru" -> {
            val translatedCityName = StringUtils.transliteration(cityName)
            StringUtils.toCityCode(translatedCityName)
        }
        iata.isEmpty() -> StringUtils.toCityCode(cityName)
        else -> iata.first()
    }
}