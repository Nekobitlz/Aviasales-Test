package com.nekobitlz.aviasales

import android.app.Application
import com.nekobitlz.aviasales.di.Injector

class AviasalesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        injector = Injector(applicationContext)
    }

    companion object {
        lateinit var injector: Injector
    }
}