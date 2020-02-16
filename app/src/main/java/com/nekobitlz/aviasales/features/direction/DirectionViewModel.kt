package com.nekobitlz.aviasales.features.direction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nekobitlz.aviasales.data.models.City
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
        directionFrom.value = City(cityName = "London", countryCode = "LON")
        directionTo.value = City(cityName = "Paris", countryCode = "PAR")
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

    fun onSwapClicked(
        cityFrom: String,
        countryCodeFrom: String,
        cityTo: String,
        countryCodeTo: String
    ) {
        directionFrom.value = City(cityName = cityTo, countryCode = countryCodeTo)
        directionTo.value = City(cityName = cityFrom, countryCode = countryCodeFrom)
    }

    private fun perform(command: RouterCommand) {
        router.postValue(SingleEvent(command))
    }
}