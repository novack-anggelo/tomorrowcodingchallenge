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
        1, 2, 3, 45, 48, 51, 53, 55, 56, 57 -> WeatherStatus.PARTIALLY_CLOUDY
        61, 63, 65, 66, 67 -> WeatherStatus.RAIN
        71, 73, 75, 77, 80, 81, 82, 85, 86 -> WeatherStatus.SNOW
        else -> WeatherStatus.THUNDER_STORM
    }