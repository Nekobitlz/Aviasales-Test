package com.nekobitlz.aviasales.utils

import android.animation.TypeEvaluator
import com.google.android.gms.maps.model.LatLng
import kotlin.math.abs
import kotlin.math.sign

class PlaneTypeEvaluator : TypeEvaluator<LatLng> {

    override fun evaluate(t: Float, start: LatLng, end: LatLng): LatLng {
        var lngDelta = end.longitude - start.longitude
        val latDelta = end.latitude - start.latitude
        val lat = t * latDelta + start.latitude
        val absLngDelta = abs(lngDelta)

        // If the city is on the other side of the map
        // e.g: Australia and the United States
        if (absLngDelta > 180) {
            lngDelta = sign(lngDelta) * (absLngDelta - 360)
        }

        val lng = t * lngDelta + start.longitude

        return LatLng(lat, lng)
    }
}