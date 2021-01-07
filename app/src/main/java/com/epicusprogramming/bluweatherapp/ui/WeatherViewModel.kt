package com.epicusprogramming.bluweatherapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epicusprogramming.bluweatherapp.models.Hourly
import com.epicusprogramming.bluweatherapp.models.WeatherResponse
import com.epicusprogramming.bluweatherapp.repositories.WeatherRepository
import com.epicusprogramming.bluweatherapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    val weatherResponse: MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()

    init {
        getWeather(-0.2, -78.51)
    }

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {

            weatherResponse.postValue(Resource.Loading())
            val response = weatherRepository.getWeather(lat, lon)
            weatherResponse.postValue(handleHourlyResponse(response))
        }
    }

    fun handleHourlyResponse(response: Response<WeatherResponse>): Resource<WeatherResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveHourlyList(hourlyList: List<Hourly>) = viewModelScope.launch {
        weatherRepository.upsertHourly(hourlyList)
    }

    fun getSavedHourly() = weatherRepository.getAllHourly()

    fun deleteHourly(hourly: Hourly) = viewModelScope.launch {
        weatherRepository.deleteHourly(hourly)
    }
}