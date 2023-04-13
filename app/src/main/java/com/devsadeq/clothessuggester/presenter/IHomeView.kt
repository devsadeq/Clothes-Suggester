package com.devsadeq.clothessuggester.presenter

import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse

interface IHomeView {
    fun onGetCurrentWeatherSuccess(weatherResponse: WeatherResponse)
    fun onGetCurrentWeatherFailure(message: String)
}