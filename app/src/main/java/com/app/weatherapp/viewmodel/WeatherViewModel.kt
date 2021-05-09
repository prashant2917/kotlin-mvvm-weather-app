package com.app.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.weatherapp.model.WeatherData
import com.app.weatherapp.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private var mutableLiveDataContactsModel = MutableLiveData<WeatherData?>()

    fun fetchWeatherData(): MutableLiveData<WeatherData?> {

        viewModelScope.launch(Dispatchers.Main) {
            val response = NetworkRepository.fetchWeatherData()
            if (response.isSuccessful) {
                response.body().let {
                    mutableLiveDataContactsModel.value = it
                }

            }

        }

        return mutableLiveDataContactsModel
    }


}