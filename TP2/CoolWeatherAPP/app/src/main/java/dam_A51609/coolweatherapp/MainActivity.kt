package dam_A51609.coolweatherapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val day = false // This should eventually come from calculation or API
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT-> {
                if (day) {
                    setTheme(R.style.Theme_Day)
                } else {
                    setTheme(R.style.Theme_Night)
                }
            }
            Configuration.ORIENTATION_LANDSCAPE-> {
                if (day) {
                    setTheme(R.style.Theme_Day_Land)
                } else {
                    setTheme(R.style.Theme_Night_Land)
                }
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val latInput: EditText = findViewById(R.id.latitudeInput)
        val longInput: EditText = findViewById(R.id.longitudeInput)
        val updateBtn: Button = findViewById(R.id.updateButton)

        // Set default Lisbon coordinates
        latInput.setText("38.076")
        longInput.setText("-9.12")

        updateBtn.setOnClickListener {
            val lat = latInput.text.toString().toFloatOrNull() ?: 0.0f
            val long = longInput.text.toString().toFloatOrNull() ?: 0.0f
            fetchWeatherData(lat, long).start()
        }

        // Initial fetch
        fetchWeatherData(38.076f, -9.12f).start()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun WeatherAPI_Call(lat: Float, long: Float) : WeatherData {
        val reqString = buildString {
            append("https://api.open-meteo.com/v1/forecast?")
            append("latitude=${lat}&longitude=${long}&")
            append("current_weather=true&")
            append("hourly=temperature_2m,weathercode,pressure_msl,windspeed_10m")
        }
        val url = URL(reqString.toString())
        url.openStream().use {
            return Gson().fromJson(InputStreamReader(it, "UTF-8"), WeatherData::class.java)
        }
    }

    private fun fetchWeatherData(lat: Float, long: Float): Thread {
        return Thread {
            val weather = WeatherAPI_Call(lat, long)
            updateUI(weather)
        }
    }

    private fun updateUI(request: WeatherData) {
        runOnUiThread {
            val weatherImage: ImageView = findViewById(R.id.weatherIcon)
            val pressure: TextView = findViewById(R.id.pressureValue)
            val temp: TextView? = findViewById(R.id.temperatureValue)
            val windSpeed: TextView? = findViewById(R.id.windSpeedValue)
            val windDir: TextView? = findViewById(R.id.windDirectionValue)
            val time: TextView? = findViewById(R.id.timeValue)

            // Populate fields
            pressure.text = "${request.current_weather.weathercode} hPa" // Actually current_weather might not have pressure, hourly has it.
            // Fitting Listing 5 logic: pressure_msl.get(12)
            pressure.text = "${request.hourly.pressure_msl[12]} hPa"
            
            temp?.text = "${request.current_weather.temperature} °C"
            windSpeed?.text = "${request.current_weather.windspeed} km/h"
            windDir?.text = "${request.current_weather.winddirection} °"
            time?.text = request.current_weather.time

            val map = getWeatherCodeMap()
            val wCode = map[request.current_weather.weathercode]
            val day = false // Simple implementation for now

            val wImage = when (wCode) {
                WMO_WeatherCode.CLEAR_SKY,
                WMO_WeatherCode.MAINLY_CLEAR,
                WMO_WeatherCode.PARTLY_CLOUDY ->
                    if (day) wCode.image + "day" else wCode.image + "night"
                else -> wCode?.image
            }

            val resID = resources.getIdentifier(wImage, "drawable", packageName)
            if (resID != 0) {
                weatherImage.setImageResource(resID)
            }
        }
    }
}