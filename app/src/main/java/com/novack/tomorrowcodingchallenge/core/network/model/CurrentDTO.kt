package com.novack.tomorrowcodingchallenge.core.network.model


data class CurrentDTO(
    val interval: Int,
    val temperature_2m: Double,
    val time: String,
    val is_day: Int,
    val weather_code: Int
)