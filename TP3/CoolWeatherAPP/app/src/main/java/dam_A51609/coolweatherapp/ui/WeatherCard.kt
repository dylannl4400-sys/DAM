package dam_A51609.coolweatherapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dam_A51609.coolweatherapp.R

@Composable
fun WeatherCard(
    seaLevelPressure: Float,
    windDirection: Int,
    windSpeed: Float,
    temperature: Float,
    time: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            WeatherRow(label = stringResource(R.string.sea_level_pressure), value = "$seaLevelPressure hPa")
            WeatherRow(label = stringResource(R.string.wind_direction), value = "$windDirection°")
            WeatherRow(label = stringResource(R.string.wind_speed), value = "$windSpeed km/h")
            WeatherRow(label = stringResource(R.string.temperature), value = "$temperature°C")
            WeatherRow(label = stringResource(R.string.time), value = time)
        }
    }
}
