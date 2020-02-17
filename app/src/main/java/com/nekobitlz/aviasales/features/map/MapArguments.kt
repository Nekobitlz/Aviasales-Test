package com.nekobitlz.aviasales.features.map

import android.os.Parcelable
import com.nekobitlz.aviasales.data.models.City
import kotlinx.android.parcel.Parcelize

@Parcelize
class MapArguments (
    val direction: Pair<City, City>
) : Parcelable