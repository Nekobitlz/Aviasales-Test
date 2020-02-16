package com.nekobitlz.aviasales.di.providers

import com.google.gson.Gson
import com.google.gson.GsonBuilder

interface IGsonProvider {
    fun get(): Gson
}

class GsonProvider : IGsonProvider {

    override fun get(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }
}