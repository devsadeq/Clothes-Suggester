package com.devsadeq.clothessuggester

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.devsadeq.clothessuggester.data.remote.response.WeatherResponse
import com.devsadeq.clothessuggester.data.remote.service.WeatherApiServiceImpl
import com.devsadeq.clothessuggester.presenter.HomePresenter
import com.devsadeq.clothessuggester.presenter.IHomeView

class MainActivity : AppCompatActivity(), IHomeView {
    private val weatherApiService by lazy { WeatherApiServiceImpl() }
    private val homePresenter by lazy { HomePresenter(this, weatherApiService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homePresenter.getCurrentWeather(35.6892, 51.3890, "metric")
    }

    override fun onGetCurrentWeatherSuccess(weatherResponse: WeatherResponse) {
        Log.d("MainActivity", weatherResponse.toString())
    }

    override fun onGetCurrentWeatherFailure(message: String) {
        Log.d("MainActivity", message)
    }
}