package com.devsadeq.clothessuggester.ui

import com.devsadeq.clothessuggester.data.local.LocalDataImpl
import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse
import com.devsadeq.clothessuggester.data.remote.WeatherApiService
import com.devsadeq.clothessuggester.util.Constants.NO_OUTFIT_AVAILABLE

class HomePresenter(
    private val view: HomeView,
    private val weatherService: WeatherApiService,
    private val localDataImpl: LocalDataImpl
) {

    fun getCurrentWeather(
        lat: Double,
        lon: Double,
        units: String,
    ) {
        view.showLoading()
        weatherService.getCurrentWeather(
            lat,
            lon,
            units,
            ::onWeatherResponseSuccess,
            ::onWeatherResponseFailure
        )
    }

    private fun onWeatherResponseSuccess(weatherResponse: WeatherResponse) {
        view.hideLoading()
        getSuggestedOutfit(weatherResponse.main.temp)
        view.showCurrentWeatherData(weatherResponse)
    }

    private fun onWeatherResponseFailure(message: String) {
        view.hideLoading()
        view.showError(message)
    }

    private fun showAllOutfitsWornErrorMessage() {
        view.showError(NO_OUTFIT_AVAILABLE)
    }

    private fun getSuggestedOutfit(temperature: Double) {
        val suggestedOutfit = localDataImpl.getSuggestedOutfit(temperature)
        if (suggestedOutfit == null) {
            showAllOutfitsWornErrorMessage()
        } else {
            view.showSuggestedOutfit(suggestedOutfit)
        }
    }

}