package com.novack.tomorrowcodingchallenge.core.network.retrofit

import com.novack.tomorrowcodingchallenge.core.network.NetworkDataSource
import com.novack.tomorrowcodingchallenge.core.network.model.WeatherResponse
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
        @Query("current") current: String = "temperature_2m,wind_speed_10m",
        @Query("hourly") hourly: String = "temperature_2m,weather_code",
        @Query("forecast_days") forecastDays: String = "1"
    ): WeatherResponse

}

@Singleton
class RetrofitNetwork @Inject constructor() : NetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(networkBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RetrofitNetworkApi::class.java)

    override suspend fun getWeatherData(lat: String, long: String): WeatherResponse =
        networkApi.getWeatherData(lat, long)

}

