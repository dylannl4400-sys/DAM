package dam_A51609.coolweatherapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dam_A51609.coolweatherapp.R
import dam_A51609.coolweatherapp.data.WMO_WeatherCode
import dam_A51609.coolweatherapp.data.getWeatherCodeMap
import dam_A51609.coolweatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherUI(weatherViewModel: WeatherViewModel = viewModel()) {
    val weatherUIState by weatherViewModel.uiState.collectAsState()
    val latitude = weatherUIState.latitude
    val longitude = weatherUIState.longitude
    val temperature = weatherUIState.temperature
    val windspeed = weatherUIState.windspeed
    val winddirection = weatherUIState.winddirection
    val weathercode = weatherUIState.weathercode
    val seaLevelPressure = weatherUIState.seaLevelPressure
    val time = weatherUIState.time
    val isLoading = weatherUIState.isLoading
    val errorMessage = weatherUIState.errorMessage

    val configuration = LocalConfiguration.current
    

    val currentHour = try {
        if (time.isNotEmpty()) {

            time.substringAfter('T').substringBefore(':').toInt()
        } else {
            java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        }
    } catch (e: Exception) {
        12
    }
    val day = currentHour in 6..19
    
    val mapt = getWeatherCodeMap()
    val wCode = mapt[weathercode]
    val wImage = when (wCode) {
        WMO_WeatherCode.CLEAR_SKY,
        WMO_WeatherCode.MAINLY_CLEAR,
        WMO_WeatherCode.PARTLY_CLOUDY -> if (day) wCode!!.image + "day" else wCode!!.image + "night"
        null -> "clear_day"
        else -> wCode.image
    }

    val context = LocalContext.current
    val wIcon = context.resources.getIdentifier(wImage, "drawable", context.packageName)
    
    // Background image
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val bgImageName = if (day) {
        if (isLandscape) "sunny_bg_land" else "sunny_bg"
    } else {
        if (isLandscape) "night_bg_land" else "night_bg"
    }
    val bgResId = context.resources.getIdentifier(bgImageName, "drawable", context.packageName)

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        if (bgResId != 0) {
            Image(
                painter = painterResource(id = bgResId),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center).padding(16.dp)
            )
        } else {
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LandscapeWeatherUI(
                    wIcon, latitude, longitude, temperature, windspeed, winddirection,
                    weathercode, seaLevelPressure, time,
                    onLatitudeChange = {
                        it.toFloatOrNull()?.let { lat -> weatherViewModel.updateLatitude(lat) }
                    },
                    onLongitudeChange = {
                        it.toFloatOrNull()?.let { lon -> weatherViewModel.updateLongitude(lon) }
                    },
                    onUpdateButtonClick = { weatherViewModel.fetchWeather() }
                )
            } else {
                PortraitWeatherUI(
                    wIcon, latitude, longitude, temperature, windspeed, winddirection,
                    weathercode, seaLevelPressure, time,
                    onLatitudeChange = {
                        it.toFloatOrNull()?.let { lat -> weatherViewModel.updateLatitude(lat) }
                    },
                    onLongitudeChange = {
                        it.toFloatOrNull()?.let { lon -> weatherViewModel.updateLongitude(lon) }
                    },
                    onUpdateButtonClick = { weatherViewModel.fetchWeather() }
                )
            }
        }
    }
}

@Composable
fun PortraitWeatherUI(
    wIcon: Int, latitude: Float, longitude: Float, temperature: Float,
    windSpeed: Float, windDirection: Int, weathercode: Int,
    seaLevelPressure: Float, time: String,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit,
    onUpdateButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (wIcon != 0) {
            Image(
                painter = painterResource(id = wIcon),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }
        CoordinatesCard(latitude, longitude, onLatitudeChange, onLongitudeChange)
        WeatherCard(seaLevelPressure, windDirection, windSpeed, temperature, time)
        Button(onClick = onUpdateButtonClick, modifier = Modifier.padding(top = 16.dp)) {
            Text(stringResource(R.string.update_weather))
        }
    }
}

@Composable
fun LandscapeWeatherUI(
    wIcon: Int, latitude: Float, longitude: Float, temperature: Float,
    windSpeed: Float, windDirection: Int, weathercode: Int,
    seaLevelPressure: Float, time: String,
    onLatitudeChange: (String) -> Unit,
    onLongitudeChange: (String) -> Unit,
    onUpdateButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (wIcon != 0) {
                Image(
                    painter = painterResource(id = wIcon),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
            CoordinatesCard(latitude, longitude, onLatitudeChange, onLongitudeChange)
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherCard(seaLevelPressure, windDirection, windSpeed, temperature, time)
            Button(onClick = onUpdateButtonClick, modifier = Modifier.padding(top = 16.dp)) {
                Text(stringResource(R.string.update_weather))
            }
        }
    }
}
