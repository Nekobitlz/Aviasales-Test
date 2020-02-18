package com.nekobitlz.aviasales.di

import android.content.Context
import com.nekobitlz.aviasales.AviasalesApp
import com.nekobitlz.aviasales.di.module.*
import com.nekobitlz.aviasales.features.direction.di.DirectionComponent
import com.nekobitlz.aviasales.features.direction.di.DirectionModule
import com.nekobitlz.aviasales.features.map.MapArguments
import com.nekobitlz.aviasales.features.map.di.MapModule
import com.nekobitlz.aviasales.features.search.di.SearchComponent
import com.nekobitlz.aviasales.features.search.di.SearchModule

class Injector(context: Context) {

    val applicationModule: ApplicationComponent = ApplicationModule(context)
    val directionModule: DirectionComponent = DirectionModule(applicationModule)
    val searchModule: SearchComponent = SearchModule(applicationModule, directionModule)

    fun getMapModule(args: MapArguments): MapModule = MapModule(args)
}

val injector: Injector get() = AviasalesApp.injector