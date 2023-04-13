package com.devsadeq.clothessuggester.data.remote.service

import com.devsadeq.clothessuggester.data.remote.response.WeatherResponse
import com.devsadeq.clothessuggester.data.util.Constants.STANDARD_UNITS

interface WeatherApiService {

    fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String = STANDARD_UNITS,
        onCurrentWeatherSuccess: (WeatherResponse) -> Unit,
        onCurrentWeatherFailure: (Throwable) -> Unit,
    )
}