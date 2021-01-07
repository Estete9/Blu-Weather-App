package com.epicusprogramming.bluweatherapp.db

import androidx.room.TypeConverter
import com.epicusprogramming.bluweatherapp.models.FeelsLike
import com.epicusprogramming.bluweatherapp.models.Rain
import com.epicusprogramming.bluweatherapp.models.Temp
import com.epicusprogramming.bluweatherapp.models.Weather

class Converters {
    @TypeConverter
    fun fromRain(rain: Rain): Double {
        return rain.`1h`
    }

    @TypeConverter
    fun toRain(`1h`: Double): Rain {
        return Rain(`1h`)
    }

    // converter from weather to String
    @TypeConverter
    fun fromWeather(weatherList: List<Weather>): String {
        return "${weatherList[0].description}, ${weatherList[0].icon}"
    }

    //    converter to weather list from weather

    @TypeConverter
    fun toWeather(weatherValues: String): List<Weather> {
        val values = weatherValues.split(", ")
        return listOf(Weather(values[0], values[1]))
    }

    //    converter from Temp to Double
    @TypeConverter
    fun fromTemp(temp: Temp): Double {
        return temp.day
    }

    //    converter to Temp from Double
    @TypeConverter
    fun toTemp(day: Double): Temp {
        return Temp(day)
    }

    @TypeConverter
    fun fromFeelsLike(feelsLike: FeelsLike): Double {
        return feelsLike.day
    }

    @TypeConverter
    fun toFeelsLike(day: Double): FeelsLike {
        return FeelsLike(day)
    }
}
