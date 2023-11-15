package com.novack.tomorrowcodingchallenge.feature.weather

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.novack.tomorrowcodingchallenge.core.data.repository.WeatherRepository
import com.novack.tomorrowcodingchallenge.core.data.util.LocationSampleData
import com.novack.tomorrowcodingchallenge.core.model.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    repository: WeatherRepository
) : ViewModel() {

    private val _fetchState: MutableStateFlow<FetchUiState> = MutableStateFlow(FetchUiState())
    val fetchUiState = _fetchState.asStateFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = FetchUiState()
        )

    val uiState: StateFlow<WeatherUiState> = LocationSampleData.dataStream().map {
        _fetchState.update { fetchUiState ->
            fetchUiState.copy(isLoading = true, isError = false)
        }
        val response = repository.getWeatherData(it)
        _fetchState.update { fetchUiState ->
            fetchUiState.copy(isLoading = false)
        }
        WeatherUiState(response)
    }.catch {
        Log.d("WeatherViewModel", it.message.orEmpty())
        _fetchState.update { fetchUiState ->
            fetchUiState.copy(isLoading = false, isError = true)
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = WeatherUiState()
        )
}

data class WeatherUiState(
    val weatherInfo: WeatherInfo? = null
)

data class FetchUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
