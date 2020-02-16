package com.nekobitlz.aviasales.di

import android.content.Context
import com.nekobitlz.aviasales.AviasalesApp
import com.nekobitlz.aviasales.di.module.*
import com.nekobitlz.aviasales.features.direction.di.DirectionComponent
import com.nekobitlz.aviasales.features.direction.di.DirectionModule
import com.nekobitlz.aviasales.features.search.di.SearchComponent
import com.nekobitlz.aviasales.features.search.di.SearchModule

class Injector(context: Context) {

    val applicationModule: ApplicationComponent = ApplicationModule()
    val directionModule: DirectionComponent = DirectionModule()
    val searchModule: SearchComponent = SearchModule(applicationModule, directionModule)
}

val injector: Injector get() = AviasalesApp.injector