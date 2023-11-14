package com.novack.tomorrowcodingchallenge.feature.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.novack.tomorrowcodingchallenge.R
import com.novack.tomorrowcodingchallenge.core.model.Coordinates

@Composable
fun WeatherScreen() {
    
}

@Composable
private fun SuccessContent() {
    Column(modifier = Modifier.fillMaxSize()) {

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
