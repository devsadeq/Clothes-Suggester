package com.devsadeq.clothessuggester.data.remote

import com.devsadeq.clothessuggester.BuildConfig
import com.devsadeq.clothessuggester.util.Constants.APPID_QUERY
import com.devsadeq.clothessuggester.util.Constants.DATA_PATH
import com.devsadeq.clothessuggester.util.Constants.HOST
import com.devsadeq.clothessuggester.util.Constants.SCHEME
import com.devsadeq.clothessuggester.util.Constants.VERSION_PATH
import com.devsadeq.clothessuggester.util.Constants.WEATHER_PATH
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class WeatherApiClient private constructor() {
    val client: OkHttpClient = OkHttpClient()
    private val httpUrlBuilder: HttpUrl.Builder = HttpUrl.Builder()

    init {
        setLoggingInterceptor()
    }

    fun getHttpUrlBuilder(): HttpUrl.Builder {
        return httpUrlBuilder
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(DATA_PATH)
            .addPathSegment(VERSION_PATH)
            .addPathSegment(WEATHER_PATH)
            .addQueryParameter(APPID_QUERY, BuildConfig.API_KEY)
    }

    private fun setLoggingInterceptor() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.newBuilder()
            .addInterceptor(logging)
            .build()
    }

    companion object {
        private var instance: WeatherApiClient? = null

        fun getInstance(): WeatherApiClient {
            if (instance == null) {
                instance = WeatherApiClient()
            }
            return instance!!
        }
    }
}