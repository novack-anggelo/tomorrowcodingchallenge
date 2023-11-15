package com.novack.tomorrowcodingchallenge.core.network.model

import com.google.gson.annotations.SerializedName
import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import com.novack.tomorrowcodingchallenge.core.model.Current
import com.novack.tomorrowcodingchallenge.core.model.Units
import com.novack.tomorrowcodingchallenge.core.model.WeatherInfo
import com.novack.tomorrowcodingchallenge.core.model.weatherCodeToWeatherStatus


data class WeatherResponseDTO(
    @SerializedName("current") val currentDTO: CurrentDTO,
    @SerializedName("current_units") val current_units: CurrentUnitsDTO,
    val elevation: Double,
    val generationtime_ms: Double,
    val hourlyDTO: HourlyDTO,
    val hourly_units: HourlyUnitsDTO,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)

fun WeatherResponseDTO.toModel() =
    WeatherInfo(
        coordinates = Coordinates(latitude.toString(), longitude.toString()),
        current = Current(
            temperature = currentDTO.temperature_2m.toString(),
            isDay = currentDTO.is_day == 1,
            weather = weatherCodeToWeatherStatus(currentDTO.weather_code)
        ),
        units = Units(temperature = current_units.temperature_2m)
    )