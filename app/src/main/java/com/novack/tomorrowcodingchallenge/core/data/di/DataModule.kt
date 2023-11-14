package com.novack.tomorrowcodingchallenge.core.data.di

import com.novack.tomorrowcodingchallenge.core.data.repository.OnlineFirstWeatherRepository
import com.novack.tomorrowcodingchallenge.core.data.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindWeatherRepository(repository: OnlineFirstWeatherRepository): WeatherRepository
}