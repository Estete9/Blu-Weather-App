package com.epicusprogramming.bluweatherapp.api

import com.epicusprogramming.bluweatherapp.models.WeatherResponse
import com.epicusprogramming.bluweatherapp.util.Constants.Companion.API_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/onecall")
    suspend fun getWeather(
        @Query("lat")
        lat: Double = -0.2,
        @Query("lon")
        lon: Double = -78.51,
        @Query("units")
        units: String = "metric",
        @Query("exclude")
        exclude: String = "minutely,alerts",
        @Query("appid")
        appId: String = API_ID
    ): Response<WeatherResponse>
}