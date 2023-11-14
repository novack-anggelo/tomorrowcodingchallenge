package com.novack.tomorrowcodingchallenge.core.data.repository

import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import com.novack.tomorrowcodingchallenge.core.network.model.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getWeatherData(coordinates: Coordinates): Flow<WeatherResponse> // TODO change return data
}