package com.epicusprogramming.bluweatherapp.models


data class Daily(

    val clouds: Double,
    val dew_point: Double,
    val dt: Long,
    val feels_like: FeelsLike,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Double,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Double,
    val wind_speed: Double
)