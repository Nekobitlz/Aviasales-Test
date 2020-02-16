package com.nekobitlz.aviasales.features.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nekobitlz.aviasales.R
import com.nekobitlz.aviasales.data.models.City
import kotlinx.android.synthetic.main.item_city.view.*

class SearchAdapter(
    private val onClick: (City) -> Unit
) : ListAdapter<City, CityViewHolder>(CityDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_city, parent, false)

        return CityViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object CityDiffUtil : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem.cityName == newItem.cityName

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
    }
}

class CityViewHolder(itemView: View, private val onClick: (City) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(city: City) {
        itemView.apply {
            tv_city_name.text = city.cityName
            tv_country_name.text = city.countryName
            tv_country_code.text = city.countryCode
            setOnClickListener { onClick(city) }
        }
    }
}