package com.epicusprogramming.bluweatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epicusprogramming.bluweatherapp.R
import com.epicusprogramming.bluweatherapp.models.Daily
import com.epicusprogramming.bluweatherapp.util.Constants.Companion.BASE_ICON_URL
import com.epicusprogramming.bluweatherapp.util.Constants.Companion.ENDING_ICON_URL
import kotlinx.android.synthetic.main.item_weather_details.view.*
import java.text.SimpleDateFormat

class DailyAdapter : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {
    inner class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var dailyWeatherList = emptyList<Daily>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        return DailyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_weather_details,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.itemView.apply {
            tvItemTemp.text = "${dailyWeatherList[position].temp.day.toInt()}°C"
            tvItemDayTime.text =
                SimpleDateFormat("EEE").format(dailyWeatherList[position].dt * 1000L)

            Glide.with(this)
                .load("$BASE_ICON_URL${dailyWeatherList[position].weather[0].icon}$ENDING_ICON_URL")
                .into(ivItemIcon)

        }
    }

    override fun getItemCount(): Int {
        return dailyWeatherList.size
    }

    fun setData(list: List<Daily>) {
        dailyWeatherList = list.subList(1, list.size - 1)
        notifyDataSetChanged()
    }
}