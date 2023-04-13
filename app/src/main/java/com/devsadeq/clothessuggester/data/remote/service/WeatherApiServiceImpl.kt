package com.devsadeq.clothessuggester.data.remote.service

import com.devsadeq.clothessuggester.data.remote.response.WeatherResponse
import com.devsadeq.clothessuggester.data.util.Constants.LAT_QUERY
import com.devsadeq.clothessuggester.data.util.Constants.LON_QUERY
import com.devsadeq.clothessuggester.data.util.Constants.UNITS_QUERY
import com.devsadeq.clothessuggester.data.util.WeatherApiClient
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class WeatherApiServiceImpl : WeatherApiService {
    private val weatherApiClient = WeatherApiClient.getInstance()
    private val gson = Gson()

    override fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String,
        onCurrentWeatherSuccess: (WeatherResponse) -> Unit,
        onCurrentWeatherFailure: (Throwable) -> Unit,
    ) {
        val url = weatherApiClient.getHttpUrlBuilder()
            .addQueryParameter(LAT_QUERY, lat.toString())
            .addQueryParameter(LON_QUERY, lon.toString())
            .addQueryParameter(UNITS_QUERY, units)
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        weatherApiClient.client
            .newCall(request)
            .enqueue(object : okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onCurrentWeatherFailure(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val weatherResponse = gson.fromJson(body, WeatherResponse::class.java)
                    onCurrentWeatherSuccess(weatherResponse)
                }
            })
    }
}