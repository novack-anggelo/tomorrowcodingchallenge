package com.novack.tomorrowcodingchallenge.core.network

import com.novack.tomorrowcodingchallenge.core.network.model.WeatherResponse

interface NetworkDataSource {
    suspend fun getWeatherData(lat: String, long: String): WeatherResponse // TODO change to domain model
}