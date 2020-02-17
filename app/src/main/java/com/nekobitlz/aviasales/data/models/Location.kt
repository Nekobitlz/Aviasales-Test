package com.nekobitlz.aviasales.data.models

import com.google.android.gms.maps.model.LatLng
import java.io.Serializable

data class Location(
    val lat: Double,
    val lon: Double
) : Serializable {
    fun toLatLng() = LatLng(lat, lon)
}