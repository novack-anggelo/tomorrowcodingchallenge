package com.novack.tomorrowcodingchallenge.core.network.retrofit

import com.novack.tomorrowcodingchallenge.core.network.NetworkDataSource
import com.novack.tomorrowcodingchallenge.core.network.model.WeatherResponseDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private const val networkBaseUrl = "https://api.open-meteo.com/v1/"

private interface RetrofitNetworkApi {

    @GET(value = "forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("current") current: String = "temperature_2m,is_day,weather_code",
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min",
        @Query("forecast_days") forecastDays: String = "7"
    ): WeatherResponseDTO

}

@Singleton
class RetrofitNetwork @Inject constructor() : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(networkBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getWeatherData(lat: String, long: String): WeatherResponseDTO =
        networkApi.getWeatherData(lat, long)

}

