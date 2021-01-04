package com.epicusprogramming.bluweatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epicusprogramming.bluweatherapp.repositories.WeatherRepository

class WeatherViewModelProviderFactory(val weatherRepository: WeatherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }
}