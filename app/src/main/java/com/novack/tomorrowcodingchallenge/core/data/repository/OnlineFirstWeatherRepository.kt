package com.novack.tomorrowcodingchallenge.core.data.repository

import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import com.novack.tomorrowcodingchallenge.core.model.WeatherInfo
import com.novack.tomorrowcodingchallenge.core.network.model.toModel
import com.novack.tomorrowcodingchallenge.core.network.retrofit.RetrofitNetwork
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnlineFirstWeatherRepository @Inject constructor(
    private val api: RetrofitNetwork
) : WeatherRepository {

    override fun getWeatherData(coordinates: Coordinates): Flow<WeatherInfo> = flow {
        emit(api.getWeatherData(coordinates.lat, coordinates.long).toModel())
    }
}