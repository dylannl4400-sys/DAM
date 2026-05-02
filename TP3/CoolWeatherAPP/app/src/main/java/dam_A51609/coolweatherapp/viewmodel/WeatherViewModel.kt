package dam_A51609.coolweatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam_A51609.coolweatherapp.data.WeatherApiClient
import dam_A51609.coolweatherapp.ui.WeatherUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUIState())
    val uiState: StateFlow<WeatherUIState> = _uiState.asStateFlow()

    init {
        fetchWeather()
    }

    fun updateLatitude(lat: Float) {
        _uiState.update { it.copy(latitude = lat) }
    }

    fun updateLongitude(lon: Float) {
        _uiState.update { it.copy(longitude = lon) }
    }

    fun fetchWeather() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            try {
                val weatherData = WeatherApiClient.getWeather(
                    _uiState.value.latitude,
                    _uiState.value.longitude
                )
                if (weatherData != null) {
                    _uiState.update {
                        it.copy(
                            temperature = weatherData.current_weather.temperature,
                            windspeed = weatherData.current_weather.windspeed,
                            winddirection = weatherData.current_weather.winddirection,
                            weathercode = weatherData.current_weather.weathercode,
                            seaLevelPressure = weatherData.hourly.pressure_msl.firstOrNull()?.toFloat() ?: 0f,
                            time = weatherData.current_weather.time,
                            isLoading = false
                        )
                    }
                } else {
                    _uiState.update { it.copy(isLoading = false, errorMessage = "Failed to fetch weather data") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.message ?: "Unknown error") }
            }
        }
    }
}
