package com.novack.tomorrowcodingchallenge.core.network.model


data class CurrentUnitsDTO(
    val interval: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String
)