package com.nekobitlz.aviasales.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nekobitlz.aviasales.data.repositories.ICityRepository
import com.nekobitlz.aviasales.features.listeners.OnCitySelectedListener

class SearchViewModelFactory(
    private val cityRepository: ICityRepository,
    private val onCitySelectedListener: OnCitySelectedListener?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(ICityRepository::class.java, OnCitySelectedListener::class.java)
            .newInstance(cityRepository, onCitySelectedListener)
    }
}