package com.novack.tomorrowcodingchallenge.core.model

data class WeatherInfo(
    val coordinates: Coordinates,
    val current: Current,
    val units: Units,
    val timezone: String,
    val daily: List<Daily>
)
