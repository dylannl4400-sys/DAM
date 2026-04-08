package dam_A51609.coolweatherapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    // Inputs (Two-way DataBinding support)
    val inputLatitude = MutableLiveData<String>("38.076")
    val inputLongitude = MutableLiveData<String>("-9.12")

    // Outputs
    private val _temperature = MutableLiveData<String>("--")
    val temperature: LiveData<String> = _temperature

    private val _pressure = MutableLiveData<String>("--")
    val pressure: LiveData<String> = _pressure

    private val _windDirection = MutableLiveData<String>("--")
    val windDirection: LiveData<String> = _windDirection

    private val _windSpeed = MutableLiveData<String>("--")
    val windSpeed: LiveData<String> = _windSpeed

    private val _time = MutableLiveData<String>("--")
    val time: LiveData<String> = _time

    // Complex state that requires resolving Context/Resources, observed by MainActivity
    private val _weatherIconRes = MutableLiveData<Int>()
    val weatherIconRes: LiveData<Int> = _weatherIconRes
    
    // We can also observe this to flip the theme if we wanted dynamic theming later
    private val _isDay = MutableLiveData<Boolean>()
    val isDay: LiveData<Boolean> = _isDay

    fun updateCoordinates(lat: String, long: String) {
        inputLatitude.postValue(lat)
        inputLongitude.postValue(long)
        fetchWeather()
    }

    fun onUpdateClicked() {
        fetchWeather()
    }

    private fun fetchWeather() {
        val latStr = inputLatitude.value ?: "0.0"
        val longStr = inputLongitude.value ?: "0.0"
        val lat = latStr.toFloatOrNull() ?: 0.0f
        val long = longStr.toFloatOrNull() ?: 0.0f

        thread {
            try {
                val reqString = buildString {
                    append("https://api.open-meteo.com/v1/forecast?")
                    append("latitude=${lat}&longitude=${long}&")
                    append("current_weather=true&")
                    append("hourly=temperature_2m,weathercode,pressure_msl,windspeed_10m")
                }
                val url = URL(reqString.toString())
                url.openStream().use {
                    val request = Gson().fromJson(InputStreamReader(it, "UTF-8"), WeatherData::class.java)
                    
                    // LiveData postValue is thread-safe, no need for runOnUiThread
                    _temperature.postValue("${request.current_weather.temperature} °C")
                    _windSpeed.postValue("${request.current_weather.windspeed} km/h")
                    _windDirection.postValue("${request.current_weather.winddirection} °")
                    _time.postValue(request.current_weather.time)
                    _pressure.postValue("${request.hourly.pressure_msl[12]} hPa")

                    val map = getWeatherCodeMap()
                    val info = map[request.current_weather.weathercode]
                    val day = false // Simple static implementation, can be calculated using sunrise/sunset
                    
                    _isDay.postValue(day)

                    val wImage = when (info?.code) {
                        0, 1, 2 -> if (day) info.image + "day" else info.image + "night"
                        else -> info?.image ?: "clear_day"
                    }
                    
                    val app = getApplication<Application>()
                    val resID = app.resources.getIdentifier(wImage, "drawable", app.packageName)
                    if (resID != 0) {
                        _weatherIconRes.postValue(resID)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getWeatherCodeMap(): Map<Int, WeatherCodeInfo> {
        val app = getApplication<Application>()
        val codes = app.resources.getIntArray(R.array.weather_codes)
        val images = app.resources.getStringArray(R.array.weather_images)
        val descriptions = app.resources.getStringArray(R.array.weather_descriptions)
        
        val weatherMap = HashMap<Int, WeatherCodeInfo>()
        for (i in codes.indices) {
            weatherMap[codes[i]] = WeatherCodeInfo(codes[i], descriptions[i], images[i])
        }
        return weatherMap
    }
}
