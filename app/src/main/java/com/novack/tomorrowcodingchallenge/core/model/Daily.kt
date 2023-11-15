package com.novack.tomorrowcodingchallenge.core.model


data class Daily(
    val date: String,
    val weather: WeatherStatus,
    val maxTemperature: String,
    val minTemperature: String
)
