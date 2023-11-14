package com.novack.tomorrowcodingchallenge.core.network

import com.novack.tomorrowcodingchallenge.core.network.model.WeatherResponseDTO

interface NetworkDataSource {
    suspend fun getWeatherData(lat: String, long: String): WeatherResponseDTO // TODO change to domain model
}