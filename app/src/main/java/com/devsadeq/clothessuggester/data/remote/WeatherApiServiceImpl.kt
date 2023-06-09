package com.devsadeq.clothessuggester.data.remote

import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse
import com.devsadeq.clothessuggester.util.Constants.LAT_QUERY
import com.devsadeq.clothessuggester.util.Constants.LON_QUERY
import com.devsadeq.clothessuggester.util.Constants.UNITS_QUERY
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class WeatherApiServiceImpl : WeatherApiService {
    private val gson = Gson()

    override fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String,
        onCurrentWeatherSuccess: (WeatherResponse) -> Unit,
        onCurrentWeatherFailure: (String) -> Unit,
    ) {
        val url = WeatherApiClient.getHttpUrlBuilder()
            .addQueryParameter(LAT_QUERY, lat.toString())
            .addQueryParameter(LON_QUERY, lon.toString())
            .addQueryParameter(UNITS_QUERY, units)
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        WeatherApiClient.client
            .newCall(request)
            .enqueue(object : okhttp3.Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onCurrentWeatherFailure(e.message.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    val weatherResponse = gson.fromJson(body, WeatherResponse::class.java)
                    onCurrentWeatherSuccess(weatherResponse)
                }
            })
    }
}