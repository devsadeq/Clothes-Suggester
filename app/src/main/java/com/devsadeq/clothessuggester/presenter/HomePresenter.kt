package com.devsadeq.clothessuggester.presenter

import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse
import com.devsadeq.clothessuggester.data.remote.WeatherApiServiceImpl

class HomePresenter(
    private val view: IHomeView,
    private val service: WeatherApiServiceImpl
) {

    fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String,
    ) {
        service.getCurrentWeather(
            lat,
            lon,
            units,
            ::onGetCurrentWeatherSuccess,
            ::onGetCurrentWeatherFailure
        )
    }

    private fun onGetCurrentWeatherSuccess(weatherResponse: WeatherResponse) {
        view.onGetCurrentWeatherSuccess(weatherResponse)
    }

    private fun onGetCurrentWeatherFailure(message: String) {
        view.onGetCurrentWeatherFailure(message)
    }

}