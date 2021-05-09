package com.app.weatherapp.model

import com.google.gson.annotations.SerializedName

class WeatherData {
    @SerializedName("data")
    var contactList: List<Weather> = emptyList()

}

class Weather(
    @SerializedName("temp")
    val temperature: Int,
    @SerializedName("time")
    val time: Long,
    @SerializedName("rain")
    val rain: Int,
    @SerializedName("wind")
    val wind: Int

)