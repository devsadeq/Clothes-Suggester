package com.devsadeq.clothessuggester.ui

import com.devsadeq.clothessuggester.data.local.LocalDataImpl
import com.devsadeq.clothessuggester.data.model.Outfit
import com.devsadeq.clothessuggester.data.model.weather.WeatherResponse
import com.devsadeq.clothessuggester.data.remote.WeatherApiService

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
        showLoading()
        weatherService.getCurrentWeather(
            lat,
            lon,
            units,
            ::showCurrentWeatherTemperature,
            ::showNetworkErrorMessage
        )
    }

    private fun showCurrentWeatherTemperature(weatherResponse: WeatherResponse) {
        hideLoading()
        getSuggestedOutfit(weatherResponse.main.temp)
        view.showCurrentWeatherTemperature(weatherResponse)
    }

    private fun showNetworkErrorMessage(message: String) {
        hideLoading()
        view.showError(message)
    }

    private fun showAllOutfitsWornErrorMessage() {
        view.showError("All outfits are worn in last two days")
    }

    private fun getSuggestedOutfit(temperature: Double) {
        val suggestedOutfit = localDataImpl.getSuggestedOutfit(temperature)
        if (suggestedOutfit == null) {
            showAllOutfitsWornErrorMessage()
        } else {
            showSuggestedOutfit(suggestedOutfit)
        }
    }

    private fun showSuggestedOutfit(outfit: Outfit) {
        view.showSuggestedOutfit(outfit)
    }

    private fun showLoading() {
        view.showLoading()
    }

    private fun hideLoading() {
        view.hideLoading()
    }

}