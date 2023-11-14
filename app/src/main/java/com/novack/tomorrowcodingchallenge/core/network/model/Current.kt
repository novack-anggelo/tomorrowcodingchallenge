package com.novack.tomorrowcodingchallenge.core.network.model


data class Current(
    val interval: Int,
    val temperature_2m: Double,
    val time: String,
    val weather_code: Int
)