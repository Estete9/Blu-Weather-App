package com.epicusprogramming.bluweatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.epicusprogramming.bluweatherapp.models.Hourly

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertHourly(hourlyList: List<Hourly>): List<Long>

    @Query("SELECT * FROM hourly")
    fun getAllHourly(): LiveData<List<Hourly>>

    @Delete
    suspend fun deleteHourly(hourly: Hourly)
}