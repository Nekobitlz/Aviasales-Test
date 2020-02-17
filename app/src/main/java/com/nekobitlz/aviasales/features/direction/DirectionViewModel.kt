package com.nekobitlz.aviasales.features.direction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nekobitlz.aviasales.data.models.City
import com.nekobitlz.aviasales.data.models.Location
import com.nekobitlz.aviasales.features.listeners.OnCitySelectedListener
import com.nekobitlz.aviasales.router.command.DirectionCommand
import com.nekobitlz.aviasales.router.command.RouterCommand
import com.nekobitlz.aviasales.utils.SingleEvent

class DirectionViewModel : ViewModel(), OnCitySelectedListener {

    private val directionFrom by lazy {
        MutableLiveData<City>()
    }
    private val directionTo by lazy {
        MutableLiveData<City>()
    }
    private val router by lazy {
        MutableLiveData<SingleEvent<RouterCommand>>()
    }

    private var isFromDirection: Boolean = false

    fun getDirectionFrom(): LiveData<City> = directionFrom
    fun getDirectionTo(): LiveData<City> = directionTo
    fun getRouter(): LiveData<SingleEvent<RouterCommand>> = router

    init {
        directionFrom.value = City(cityName = "London", countryCode = "GB", location = Location(51.500729,-0.124627))
        directionTo.value = City(cityName = "Paris", countryCode = "FR", location = Location(48.85634,2.342587))
    }

    override fun onCitySelected(city: City) {
        if (isFromDirection) {
            directionFrom.value = city
        } else {
            directionTo.value = city
        }
    }

    fun onDirectionFromClicked() {
        perform(DirectionCommand())
        isFromDirection = true
    }

    fun onDirectionToClicked() {
        perform(DirectionCommand())
        isFromDirection = false
    }

    fun onSearchClicked() {
    }

    fun onSwapClicked() {
        val helper = directionFrom.value
        directionFrom.value = directionTo.value
        directionTo.value = helper
    }

    private fun perform(command: RouterCommand) {
        router.postValue(SingleEvent(command))
    }
}