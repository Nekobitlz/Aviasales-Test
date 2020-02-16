package com.nekobitlz.aviasales.features.search.di

import com.nekobitlz.aviasales.di.module.ApplicationComponent
import com.nekobitlz.aviasales.features.direction.di.DirectionComponent
import com.nekobitlz.aviasales.features.search.SearchViewModelFactory

interface SearchComponent {
    val searchViewModelFactory: SearchViewModelFactory
}

class SearchModule(private val appComponent: ApplicationComponent, private val directionComponent: DirectionComponent) :
    SearchComponent {

    override val searchViewModelFactory
        get() = SearchViewModelFactory(
            appComponent.cityRepository,
            directionComponent.onCitySelectedListener
        )
}
