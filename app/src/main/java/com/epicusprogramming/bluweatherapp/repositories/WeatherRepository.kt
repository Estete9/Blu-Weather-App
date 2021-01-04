package com.epicusprogramming.bluweatherapp.repositories

import com.epicusprogramming.bluweatherapp.api.RetrofitInstance
import com.epicusprogramming.bluweatherapp.models.WeatherResponse
import retrofit2.Response


class WeatherRepository(
//    hourlyDb: HourlyDatabase
) {
    suspend fun getWeather(
        lat: Double,
        lon: Double,

        ): Response<WeatherResponse> {
        return RetrofitInstance.api.getWeather(lat, lon)
    }

}