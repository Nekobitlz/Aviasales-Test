package com.nekobitlz.aviasales.features.listeners

import com.nekobitlz.aviasales.data.models.City

interface OnCitySelectedListener {
    fun onCitySelected(city: City)
}