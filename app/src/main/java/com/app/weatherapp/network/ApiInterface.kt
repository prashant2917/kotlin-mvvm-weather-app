package com.app.weatherapp.network
import com.app.weatherapp.model.WeatherData
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("5d3a99ed2f0000bac16ec13a")
    suspend fun getWeatherData(): Response<WeatherData>


}