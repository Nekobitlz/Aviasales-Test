package com.nekobitlz.aviasales.features.direction.di

import com.nekobitlz.aviasales.di.module.ApplicationComponent
import com.nekobitlz.aviasales.features.direction.DirectionViewModelFactory
import com.nekobitlz.aviasales.features.listeners.OnCitySelectedListener

interface DirectionComponent {
    var onCitySelectedListener: OnCitySelectedListener?
    val directionViewModelFactory: DirectionViewModelFactory
}

class DirectionModule(private val applicationComponent: ApplicationComponent) : DirectionComponent {

    override var onCitySelectedListener: OnCitySelectedListener? = null

    override val directionViewModelFactory: DirectionViewModelFactory
        get() = DirectionViewModelFactory(applicationComponent.resourceProvider)
}