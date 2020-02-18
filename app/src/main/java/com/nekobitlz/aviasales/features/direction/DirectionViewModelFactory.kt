package com.nekobitlz.aviasales.features.direction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nekobitlz.aviasales.di.providers.IResourceProvider

class DirectionViewModelFactory(private val resourceProvider: IResourceProvider) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(IResourceProvider::class.java)
            .newInstance(resourceProvider)
    }
}