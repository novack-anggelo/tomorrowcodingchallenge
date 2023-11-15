package com.novack.tomorrowcodingchallenge.core.model

enum class WeatherStatus {
    CLEAR_SKY,
    PARTIALLY_CLOUDY,
    RAIN,
    SNOW,
    THUNDER_STORM
}

fun weatherCodeToWeatherStatus(code: Int) =
    when(code) {
        0 -> WeatherStatus.CLEAR_SKY
        in 2..61 -> WeatherStatus.PARTIALLY_CLOUDY
        in 63..72 -> WeatherStatus.RAIN
        in 73..86 -> WeatherStatus.SNOW
        else -> WeatherStatus.THUNDER_STORM
    }