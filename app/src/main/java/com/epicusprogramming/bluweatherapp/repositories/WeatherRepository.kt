package com.epicusprogramming.bluweatherapp.repositories

import com.epicusprogramming.bluweatherapp.api.RetrofitInstance
import com.epicusprogramming.bluweatherapp.db.HourlyDatabase
import com.epicusprogramming.bluweatherapp.models.Hourly
import com.epicusprogramming.bluweatherapp.models.WeatherResponse
import com.epicusprogramming.bluweatherapp.util.Resource
import retrofit2.Response


class WeatherRepository(
    val hourlyDb: HourlyDatabase
//    val
) {
    suspend fun getWeather(
        lat: Double,
        lon: Double,

        ): Response<WeatherResponse> {
        return RetrofitInstance.api.getWeather(lat, lon)
    }

    suspend fun upsertHourly(hourlyList: List<Hourly>) = hourlyDb.getWeatherDao().upsertHourly(hourlyList)

    fun getAllHourly() = hourlyDb.getWeatherDao().getAllHourly()

    suspend fun deleteHourly(hourly: Hourly) = hourlyDb.getWeatherDao().deleteHourly(hourly)
}