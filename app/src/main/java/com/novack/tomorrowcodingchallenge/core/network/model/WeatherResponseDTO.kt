package com.novack.tomorrowcodingchallenge.core.network.model

import android.util.Log
import com.google.gson.annotations.SerializedName
import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import com.novack.tomorrowcodingchallenge.core.model.Current
import com.novack.tomorrowcodingchallenge.core.model.Daily
import com.novack.tomorrowcodingchallenge.core.model.Units
import com.novack.tomorrowcodingchallenge.core.model.WeatherInfo
import com.novack.tomorrowcodingchallenge.core.model.weatherCodeToWeatherStatus


data class WeatherResponseDTO(
    @SerializedName("current") val currentDTO: CurrentDTO,
    @SerializedName("current_units") val current_units: CurrentUnitsDTO,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int,
    @SerializedName("daily") val dailyDTO: DailyDTO
)

fun WeatherResponseDTO.toModel() =
    WeatherInfo(
        coordinates = Coordinates(latitude.toString(), longitude.toString()),
        current = Current(
            temperature = currentDTO.temperature_2m.toString(),
            isDay = currentDTO.is_day == 1,
            weather = weatherCodeToWeatherStatus(currentDTO.weather_code)
        ),
        units = Units(temperature = current_units.temperature_2m),
        timezone = timezone,
        daily = mapDailyData(dailyDTO)
    )

private fun mapDailyData(dailyDTO: DailyDTO): List<Daily> {
    val dailyData = mutableListOf<Daily>()
    dailyDTO.weather_code.forEachIndexed { index, day ->
        try {
            dailyData.add(
                Daily(
                    date = dailyDTO.time[index],
                    weather = weatherCodeToWeatherStatus(day) ,
                    minTemperature = dailyDTO.temperature_2m_min[index].toString(),
                    maxTemperature = dailyDTO.temperature_2m_max[index].toString()
                )
            )
        } catch (e: Exception) {
            Log.d("mapDailyData", e.message ?: "")
        }
    }

    return dailyData
}