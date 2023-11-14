package com.novack.tomorrowcodingchallenge.core.model

data class Current(
    val temperature: String,
    val isDay: Boolean,
    val weather: WeatherStatus
)
