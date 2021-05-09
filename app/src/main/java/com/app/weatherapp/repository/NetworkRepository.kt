package com.app.weatherapp.repository
import com.app.weatherapp.model.WeatherData
import com.app.weatherapp.network.ApiInterface
import com.app.weatherapp.network.RetrofitBuilder

import retrofit2.Response

object NetworkRepository {

    suspend fun fetchWeatherData(): Response<WeatherData> {
        return RetrofitBuilder.buildService(ApiInterface::class.java).getWeatherData()

    }
}