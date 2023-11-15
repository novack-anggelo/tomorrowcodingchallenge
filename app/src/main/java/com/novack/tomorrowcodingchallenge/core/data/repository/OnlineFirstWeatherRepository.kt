package com.novack.tomorrowcodingchallenge.core.data.repository

import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import com.novack.tomorrowcodingchallenge.core.model.WeatherInfo
import com.novack.tomorrowcodingchallenge.core.network.model.toModel
import com.novack.tomorrowcodingchallenge.core.network.retrofit.RetrofitNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnlineFirstWeatherRepository @Inject constructor(
    private val api: RetrofitNetwork
) : WeatherRepository {

    override suspend fun getWeatherData(coordinates: Coordinates): WeatherInfo = withContext(Dispatchers.IO) {
        api.getWeatherData(coordinates.lat, coordinates.long).toModel()
    }

}