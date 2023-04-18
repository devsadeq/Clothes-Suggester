package com.devsadeq.clothessuggester.data.remote

import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse

interface WeatherApiService {

    fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String,
        onCurrentWeatherSuccess: (WeatherResponse) -> Unit,
        onCurrentWeatherFailure: (String) -> Unit,
    )
}