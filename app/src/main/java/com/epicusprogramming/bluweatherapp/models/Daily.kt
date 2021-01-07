package com.epicusprogramming.bluweatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "daily"
)
data class Daily(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
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