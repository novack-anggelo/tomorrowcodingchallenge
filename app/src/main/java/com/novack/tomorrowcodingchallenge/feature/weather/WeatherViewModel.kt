package com.novack.tomorrowcodingchallenge.feature.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novack.tomorrowcodingchallenge.core.data.repository.WeatherRepository
import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    repository: WeatherRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            val test = repository.getWeatherData(Coordinates("52.52","13.41999")).flowOn(Dispatchers.IO)
                .collect {
                    val asd = it
                }
        }

    }
}
