package com.epicusprogramming.bluweatherapp.ui

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.epicusprogramming.bluweatherapp.R
import com.epicusprogramming.bluweatherapp.db.HourlyDatabase
import com.epicusprogramming.bluweatherapp.repositories.WeatherRepository
import com.epicusprogramming.bluweatherapp.util.Constants.Companion.BASE_ICON_URL
import com.epicusprogramming.bluweatherapp.util.Constants.Companion.ENDING_ICON_URL
import com.epicusprogramming.bluweatherapp.util.Resource
import kotlinx.android.synthetic.main.activity_weather.*
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    lateinit var weatherViewModel: WeatherViewModel

    val TAG = "WeatherActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val hourlyWeatherRepository = WeatherRepository(HourlyDatabase(this))
        val viewModelProviderFactory = WeatherViewModelProviderFactory(hourlyWeatherRepository)
        val gps = Geocoder(this).getFromLocation(-0.2, -78.51, 1)[0]

        setDateTimeCountry(gps, tvCity, tvDate, tvTime)

        weatherViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(WeatherViewModel::class.java)

        weatherViewModel.weatherResponse.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { weatherResponse ->
                        tvWeatherDescription.text = weatherResponse.current.weather[0].description
                        tvTemp.text = "${weatherResponse.current.temp.toInt()}°C"

                        Glide.with(this)
                            .load("$BASE_ICON_URL${weatherResponse.current.weather[0].icon}$ENDING_ICON_URL")
                            .into(ivIcon)
                    }

                }
                is Resource.Error -> {
                    Log.e(TAG, "error occurred: ${response.message}")
                }
                is Resource.Loading -> {
                    Log.d(TAG, "response loading")
                }
            }

        })
    }

    private fun setDateTimeCountry(
        gps: Address,
        tvCity: TextView,
        tvDate: TextView,
        tvTime: TextView
    ) {
        tvCity.text = "${gps.locality}, ${gps.countryCode}"
        tvDate.text =
            SimpleDateFormat("EEE, MMMM d").format(Calendar.getInstance().timeInMillis / 1000L)
        tvTime.text =
            SimpleDateFormat("hh:mm a").format(Calendar.getInstance().timeInMillis / 1000L)
    }
}