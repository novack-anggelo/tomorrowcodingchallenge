package com.novack.tomorrowcodingchallenge.core.network.model

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("current")val current: Current,
    val current_units: CurrentUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)