package com.novack.tomorrowcodingchallenge.feature.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novack.tomorrowcodingchallenge.core.data.repository.WeatherRepository
import com.novack.tomorrowcodingchallenge.core.data.util.LocationSampleData
import com.novack.tomorrowcodingchallenge.core.model.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    repository: WeatherRepository
) : ViewModel() {

    val uiState: StateFlow<WeatherUiState> = LocationSampleData.dataStream().map {
        val response = repository.getWeatherData(it)
        WeatherUiState.Success(response)
    }
        .catch<WeatherUiState> {
            emit(WeatherUiState.Error)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = WeatherUiState.Loading
        )
}

sealed interface WeatherUiState {
    object Loading : WeatherUiState
    object Error : WeatherUiState
    data class Success(
        val weatherInfo: WeatherInfo
    ) : WeatherUiState
}
