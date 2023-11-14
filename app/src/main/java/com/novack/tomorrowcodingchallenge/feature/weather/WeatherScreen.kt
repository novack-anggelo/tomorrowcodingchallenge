package com.novack.tomorrowcodingchallenge.feature.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    WeatherScreen(uiState = uiState)
}

@Composable
fun WeatherScreen(
    uiState: WeatherUiState
) {
    when(uiState) {
        is WeatherUiState.Loading -> {}
        is WeatherUiState.Error -> {}
        is WeatherUiState.Success -> {
            SuccessContent(uiState = uiState)
        }
    }
}

@Composable
private fun SuccessContent(
    uiState: WeatherUiState.Success
) {
    Column(modifier = Modifier.fillMaxSize()) {
        WeatherHeader(
            weatherIconResource = getWeatherIcon(uiState.weatherInfo.current.weather),
            coordinates = Coordinates("12.56", "34.65"),
            temperature = uiState.weatherInfo.current.temperature,
            temperatureUnit = uiState.weatherInfo.units.temperature,
            timeZone = "Berlin" ,
            weather = stringResource(id = getWeatherDescription(uiState.weatherInfo.current.weather))
        )
    }
}

// TODO Style
@Composable
private fun WeatherHeader(
    @DrawableRes weatherIconResource: Int,
    coordinates: Coordinates,
    temperature: String,
    temperatureUnit: String,
    timeZone: String,
    weather: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = weatherIconResource),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(45.dp)
        )
        Text(text = "$temperature $temperatureUnit")
        Text(text = weather)
        Text(text = timeZone)
        Text(text = stringResource(id = R.string.COORDINATES, coordinates.lat, coordinates.long))
    }
}

private fun getWeatherIcon(weatherStatus: WeatherStatus) =
    when (weatherStatus) {
        WeatherStatus.CLEAR_SKY -> R.drawable.sun
        WeatherStatus.PARTIALLY_CLOUDY -> R.drawable.partially_sunny
        WeatherStatus.RAIN -> R.drawable.rain
        WeatherStatus.SNOW -> R.drawable.snow
        WeatherStatus.THUNDER_STORM -> R.drawable.heavy_storm
    }

private fun getWeatherDescription(weatherStatus: WeatherStatus) =
    when (weatherStatus) {
        WeatherStatus.CLEAR_SKY -> R.string.WEATHER_STATUS_SUNNY
        WeatherStatus.PARTIALLY_CLOUDY -> R.string.WEATHER_STATUS_PARTIALLY_CLOUDY
        WeatherStatus.RAIN -> R.string.WEATHER_STATUS_RAIN
        WeatherStatus.SNOW -> R.string.WEATHER_STATUS_SNOW
        WeatherStatus.THUNDER_STORM -> R.string.WEATHER_STATUS_THUNDERSTORM
    }

@Composable
private fun ErrorContent() {

}

@Preview(showBackground = true)
@Composable
private fun WeatherHeaderPreview() {
    WeatherHeader(
        weatherIconResource = R.drawable.sun,
        coordinates = Coordinates("12.56", "34.65"),
        temperature = "20",
        temperatureUnit = "°C",
        timeZone = "Berlin",
        weather = "Sunny"
    )
}
