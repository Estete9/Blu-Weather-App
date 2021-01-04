package com.epicusprogramming.bluweatherapp.models

data class Weather(
    val description: String,
    val icon: String,
    val id: Long,
    val main: String
)