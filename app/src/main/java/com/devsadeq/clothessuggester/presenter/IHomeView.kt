package com.devsadeq.clothessuggester.presenter

import com.devsadeq.clothessuggester.data.remote.response.WeatherResponse

interface IHomeView {
    fun onGetCurrentWeatherSuccess(weatherResponse: WeatherResponse)
    fun onGetCurrentWeatherFailure(message: String)
}