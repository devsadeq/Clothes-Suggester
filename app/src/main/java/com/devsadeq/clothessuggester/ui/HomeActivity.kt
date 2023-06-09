package com.devsadeq.clothessuggester.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devsadeq.clothessuggester.data.local.LocalDataImpl
import com.devsadeq.clothessuggester.data.model.Outfit
import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse
import com.devsadeq.clothessuggester.data.remote.WeatherApiServiceImpl
import com.devsadeq.clothessuggester.databinding.ActivityHomeBinding
import com.devsadeq.clothessuggester.util.Constants.SHARED_PREF_NAME
import com.devsadeq.clothessuggester.util.loadImageFromUrl

class HomeActivity : AppCompatActivity(), HomeView {
    private val weatherApiService by lazy { WeatherApiServiceImpl() }
    private val sharedPref by lazy { getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE) }
    private val localDataImpl by lazy { LocalDataImpl(sharedPref) }
    private val homePresenter by lazy { HomePresenter(this, weatherApiService, localDataImpl) }
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homePresenter.getCurrentWeather(BAGHDAD_LAT, BAGHDAD_LON, UNITS_METRIC)
    }

    override fun showCurrentWeatherData(weatherResponse: WeatherResponse) {
        runOnUiThread {
            binding.apply {
                textViewTemperature.text =
                    weatherResponse.main.temp.toInt().toString() + CELSIUS_SYMBOL
                textViewState.text = weatherResponse.weather.first().description
                imageViewIcon.loadImageFromUrl(IMAGE_BASE_URL + weatherResponse.weather.first().icon + IMAGE_EXTENSION)
            }
        }
    }

    override fun showError(message: String) {
        runOnUiThread {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showSuggestedOutfit(outfit: Outfit) {
        runOnUiThread {
            binding.imageViewOutfit.setImageResource(outfit.imageResource)
        }
    }

    override fun showLoading() {
        runOnUiThread {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        runOnUiThread {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val BAGHDAD_LAT = 33.34
        const val BAGHDAD_LON = 44.4
        const val UNITS_METRIC = "metric"
        const val IMAGE_BASE_URL = "https://openweathermap.org/img/wn/"
        const val IMAGE_EXTENSION = ".png"
        const val CELSIUS_SYMBOL = "°C"
    }
}