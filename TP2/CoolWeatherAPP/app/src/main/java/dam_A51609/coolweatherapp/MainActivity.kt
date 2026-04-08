package dam_A51609.coolweatherapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import dam_A51609.coolweatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Observer Pattern: Observe elements that interact with the Android Context
        viewModel.weatherIconRes.observe(this) { resId ->
            binding.weatherIcon.setImageResource(R.drawable.fog) // Mock loading default
            val drawable = this.getDrawable(resId)
            binding.weatherIcon.setImageDrawable(drawable)
        }

        viewModel.isDay.observe(this) { isDay ->
            val isPortrait = resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
            val bgRes = if (isPortrait) {
                if (isDay) R.drawable.sunny_bg else R.drawable.night_bg
            } else {
                if (isDay) R.drawable.sunny_bg_land else R.drawable.night_bg_land
            }
            binding.main.setBackgroundResource(bgRes)
        }

        requestLocationAndFetchWeather()
    }

    private fun requestLocationAndFetchWeather() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            fetchLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            fetchLocation()
        } else {
            fallbackToDefaultLocation()
        }
    }

    private fun fallbackToDefaultLocation() {
        viewModel.updateCoordinates("38.076", "-9.12")
    }

    private fun fetchLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            var location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }

            if (location != null) {
                val lat = location.latitude.toFloat()
                val long = location.longitude.toFloat()
                viewModel.updateCoordinates(lat.toString(), long.toString())
            } else {
                fallbackToDefaultLocation()
            }
        } catch (e: SecurityException) {
             fallbackToDefaultLocation()
        }
    }
}