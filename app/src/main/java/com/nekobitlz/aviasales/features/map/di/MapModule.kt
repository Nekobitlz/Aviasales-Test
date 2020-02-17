package com.nekobitlz.aviasales.features.map.di

import com.nekobitlz.aviasales.features.map.MapArguments
import com.nekobitlz.aviasales.features.map.MapViewModelFactory

interface MapComponent {
    val mapViewModelFactory: MapViewModelFactory
}

class MapModule(private val args: MapArguments) : MapComponent {
    override val mapViewModelFactory: MapViewModelFactory
        get() = MapViewModelFactory(args = args)

}