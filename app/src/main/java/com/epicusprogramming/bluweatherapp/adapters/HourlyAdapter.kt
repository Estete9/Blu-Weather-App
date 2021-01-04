package com.epicusprogramming.bluweatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epicusprogramming.bluweatherapp.R
import com.epicusprogramming.bluweatherapp.models.Hourly
import com.epicusprogramming.bluweatherapp.util.Constants.Companion.BASE_ICON_URL
import com.epicusprogramming.bluweatherapp.util.Constants.Companion.ENDING_ICON_URL
import kotlinx.android.synthetic.main.item_weather_details.view.*
import java.text.SimpleDateFormat

class HourlyAdapter : RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {
    inner class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var hourlyWeatherList = emptyList<Hourly>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        return HourlyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_weather_details,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        holder.itemView.apply {
            tvItemTemp.text = "${hourlyWeatherList[position].temp.toInt()}°C"
            tvItemDayTime.text =
                SimpleDateFormat("h a").format(hourlyWeatherList[position].dt * 1000L)

            Glide.with(this)
                .load("$BASE_ICON_URL${hourlyWeatherList[position].weather[0].icon}$ENDING_ICON_URL")
                .into(ivItemIcon)
        }
    }

    override fun getItemCount(): Int {
        return hourlyWeatherList.size
    }

    fun setData(list: List<Hourly>) {
        hourlyWeatherList = list.subList(1, 25)
        notifyDataSetChanged()
    }
}