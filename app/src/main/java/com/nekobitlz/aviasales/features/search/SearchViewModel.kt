package com.nekobitlz.aviasales.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nekobitlz.aviasales.data.models.City
import com.nekobitlz.aviasales.data.repositories.ICityRepository
import com.nekobitlz.aviasales.features.listeners.OnCitySelectedListener
import com.nekobitlz.aviasales.router.command.RouterCommand
import com.nekobitlz.aviasales.router.command.SearchCommand
import com.nekobitlz.aviasales.utils.SingleEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel(
    private val repository: ICityRepository,
    private var onCitySelectedListener: OnCitySelectedListener?
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val cities by lazy {
        MutableLiveData<List<City>>()
    }

    private val router by lazy {
        MutableLiveData<SingleEvent<RouterCommand>>()
    }

    fun getCities(): LiveData<List<City>> = cities
    fun getRouter(): LiveData<SingleEvent<RouterCommand>> = router

    fun onTextChanged(text: String) {
        disposable.add(repository.getCities(text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { success, error ->
                if (error != null) {
                    error.printStackTrace()
                } else {
                    cities.value = success.cities

                }
            })
    }

    fun onItemClicked(city: City) {
        onCitySelectedListener?.onCitySelected(city)
        onBackPressed()
    }

    fun onBackPressed() {
        perform(SearchCommand())
    }

    private fun perform(command: RouterCommand) {
        router.postValue(SingleEvent(command))
    }

    override fun onCleared() {
        disposable.dispose()
        onCitySelectedListener = null
        super.onCleared()
    }
}