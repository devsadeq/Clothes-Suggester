package com.devsadeq.clothessuggester.ui

import com.devsadeq.clothessuggester.data.model.Outfit
import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse

interface HomeView {
    fun showCurrentWeatherTemperature(weatherResponse: WeatherResponse)

    fun showError(message: String)

    fun showSuggestedOutfit(outfit: Outfit)

    fun showLoading()

    fun hideLoading()
}