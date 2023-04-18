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

object WeatherApiClient {
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpUrlBuilder = HttpUrl.Builder()
    val client = OkHttpClient().newBuilder()
        .addInterceptor(logging)
        .build()

    fun getHttpUrlBuilder(): HttpUrl.Builder {
        return httpUrlBuilder
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(DATA_PATH)
            .addPathSegment(VERSION_PATH)
            .addPathSegment(WEATHER_PATH)
            .addQueryParameter(APPID_QUERY, BuildConfig.API_KEY)
    }
}