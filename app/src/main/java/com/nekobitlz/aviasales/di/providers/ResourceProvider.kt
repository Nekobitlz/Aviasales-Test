package com.nekobitlz.aviasales.di.providers

import android.content.Context

interface IResourceProvider {
    fun getString(resId: Int): String
}

class ResourceProvider(private val context: Context) : IResourceProvider {

    override fun getString(resId: Int): String = context.getString(resId)

}