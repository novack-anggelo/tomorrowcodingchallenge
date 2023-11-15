package com.novack.tomorrowcodingchallenge.core.network.model

data class DailyDTO(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>
)
