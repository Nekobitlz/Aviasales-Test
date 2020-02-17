package com.nekobitlz.aviasales.features.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nekobitlz.aviasales.data.models.City
import com.nekobitlz.aviasales.utils.SingleEvent

class MapViewModel(private val args: MapArguments) : ViewModel() {
    private val animationEvent by lazy {
        MutableLiveData<SingleEvent<Pair<City, City>>>()
    }

    fun getAnimationEvent(): LiveData<SingleEvent<Pair<City, City>>> = animationEvent

    fun onMapReady() {
        animationEvent.postValue(SingleEvent(args.direction))
    }
}