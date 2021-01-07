package com.epicusprogramming.bluweatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.epicusprogramming.bluweatherapp.models.Daily
import com.epicusprogramming.bluweatherapp.models.Hourly

@Database(
    entities = [Hourly::class, Daily::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class HourlyDatabase : RoomDatabase() {
    abstract fun getWeatherDao(): WeatherDao

    companion object {
        private var instance: HourlyDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createHourlyDatabase(context).also { instance = it }
        }

        private fun createHourlyDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HourlyDatabase::class.java,
                "hourly_db.db"
            ).build()
    }
}