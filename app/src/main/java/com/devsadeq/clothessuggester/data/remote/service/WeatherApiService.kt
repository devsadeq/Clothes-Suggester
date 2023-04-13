package com.devsadeq.clothessuggester.data.remote.service

import com.devsadeq.clothessuggester.data.remote.response.WeatherResponse

interface WeatherApiService {

    fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String,
        onCurrentWeatherSuccess: (WeatherResponse) -> Unit,
        onCurrentWeatherFailure: (String) -> Unit,
    )
}