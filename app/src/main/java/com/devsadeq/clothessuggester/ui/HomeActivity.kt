package com.devsadeq.clothessuggester.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.devsadeq.clothessuggester.R
import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse
import com.devsadeq.clothessuggester.data.remote.WeatherApiServiceImpl
import com.devsadeq.clothessuggester.presenter.HomePresenter
import com.devsadeq.clothessuggester.presenter.IHomeView

class HomeActivity : AppCompatActivity(), IHomeView {
    private val weatherApiService by lazy { WeatherApiServiceImpl() }
    private val homePresenter by lazy { HomePresenter(this, weatherApiService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homePresenter.getCurrentWeather(35.6892, 51.3890, "metric")
    }

    override fun onGetCurrentWeatherSuccess(weatherResponse: WeatherResponse) {
        Log.d("MainActivity", weatherResponse.toString())
    }

    override fun onGetCurrentWeatherFailure(message: String) {
        Log.d("MainActivity", message)
    }
}