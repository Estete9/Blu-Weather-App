package com.epicusprogramming.bluweatherapp.util

import com.epicusprogramming.bluweatherapp.BuildConfig

class Constants {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org"
        const val BASE_ICON_URL = "http://openweathermap.org/img/wn/"
        const val ENDING_ICON_URL = "@2x.png"
        const val API_ID = BuildConfig.OPEN_WEATHER_API
    }
}