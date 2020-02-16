package com.nekobitlz.aviasales.features.direction.di

import com.nekobitlz.aviasales.features.listeners.OnCitySelectedListener

interface DirectionComponent {
    var onCitySelectedListener: OnCitySelectedListener?
}

class DirectionModule : DirectionComponent {
    override var onCitySelectedListener: OnCitySelectedListener? = null
}