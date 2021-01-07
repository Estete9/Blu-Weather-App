package com.epicusprogramming.bluweatherapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "hourly"
)
data class Hourly(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val clouds: Double,
    val dew_point: Double,
    val dt: Long,
    val feels_like: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Rain,
    val temp: Double,
    val uvi: Double,
    val visibility: Double,
    val weather: List<Weather>,
    val wind_deg: Double,
    val wind_speed: Double
)