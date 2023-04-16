package com.devsadeq.clothessuggester.ui

import com.devsadeq.clothessuggester.data.model.Clothing
import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse

interface HomeView {
    fun onGetCurrentWeatherSuccess(weatherResponse: WeatherResponse)
    fun onGetCurrentWeatherFailure(message: String)
    fun presentSuggestClothes(clothes: List<Clothing>)
}