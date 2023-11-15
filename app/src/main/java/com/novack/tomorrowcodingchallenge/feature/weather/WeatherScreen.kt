package com.novack.tomorrowcodingchallenge.feature.weather

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.novack.tomorrowcodingchallenge.R
import com.novack.tomorrowcodingchallenge.core.model.Coordinates
import com.novack.tomorrowcodingchallenge.core.model.WeatherStatus

@Composable
fun WeatherRoute(viewModel: WeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val fetchUiState by viewModel.fetchUiState.collectAsStateWithLifecycle()

    WeatherScreen(uiState = uiState, fetchUiState = fetchUiState)
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState,
    fetchUiState: FetchUiState
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = fetchUiState.isError) {
        if (fetchUiState.isError) {
            Toast.makeText(context, R.string.WEATHER_FETCH_ERROR, Toast.LENGTH_LONG).show()
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (fetchUiState.isLoading) {
            LoadingContent()
        }
        WeatherDataContent(uiState = uiState)
    }
}

@Composable
private fun WeatherDataContent(
    uiState: WeatherUiState
) {
    Column(modifier = Modifier.fillMaxSize()) {
        WeatherHeader(
            weatherIconResource = getWeatherIcon(
                uiState.weatherInfo?.current?.weather,
                uiState.weatherInfo?.current?.isDay
            ),
            coordinates = uiState.weatherInfo?.coordinates,
            temperature = uiState.weatherInfo?.current?.temperature,
            temperatureUnit = uiState.weatherInfo?.units?.temperature,
            weather = getWeatherDescription(uiState.weatherInfo?.current?.weather)?.let {
                stringResource(
                    id = it
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
    }
}

@Composable
private fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            strokeWidth = 5.dp,
            modifier = Modifier.fillMaxSize(0.25f)
        )
    }
}

// TODO Style
@Composable
private fun WeatherHeader(
    @DrawableRes weatherIconResource: Int?,
    coordinates: Coordinates?,
    temperature: String?,
    temperatureUnit: String?,
    weather: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = weatherIconResource ?: R.drawable.no_data_icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(45.dp)
        )
        Text(text = temperature?.let { "$it $temperatureUnit" } ?: "")
        Text(text = weather.orEmpty())
        Text(text = coordinates?.let { stringResource(id = R.string.COORDINATES, it.lat, it.long) }
            ?: "")
    }
}

private fun getWeatherIcon(weatherStatus: WeatherStatus?, isDay: Boolean?): Int? {
    if (weatherStatus == null || isDay == null) return null

    return when (weatherStatus) {
        WeatherStatus.CLEAR_SKY -> if (isDay) R.drawable.sun else R.drawable.moon
        WeatherStatus.PARTIALLY_CLOUDY -> if (isDay) R.drawable.partially_sunny else R.drawable.moon_cloudy
        WeatherStatus.RAIN -> R.drawable.rain
        WeatherStatus.SNOW -> R.drawable.snow
        WeatherStatus.THUNDER_STORM -> R.drawable.heavy_storm
    }
}


private fun getWeatherDescription(weatherStatus: WeatherStatus?): Int? {
    return when (weatherStatus) {
        WeatherStatus.CLEAR_SKY -> R.string.WEATHER_STATUS_CLEAR
        WeatherStatus.PARTIALLY_CLOUDY -> R.string.WEATHER_STATUS_PARTIALLY_CLOUDY
        WeatherStatus.RAIN -> R.string.WEATHER_STATUS_RAIN
        WeatherStatus.SNOW -> R.string.WEATHER_STATUS_SNOW
        WeatherStatus.THUNDER_STORM -> R.string.WEATHER_STATUS_THUNDERSTORM
        else -> null
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherHeaderPreview() {
    WeatherHeader(
        weatherIconResource = R.drawable.sun,
        coordinates = Coordinates("12.56", "34.65"),
        temperature = "20",
        temperatureUnit = "°C",
        weather = "Sunny"
    )
}
