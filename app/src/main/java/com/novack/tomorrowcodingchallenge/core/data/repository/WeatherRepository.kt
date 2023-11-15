package com.novack.tomorrowcodingchallenge.core.data.repository

import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import com.novack.tomorrowcodingchallenge.core.model.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(coordinates: Coordinates): WeatherInfo
}