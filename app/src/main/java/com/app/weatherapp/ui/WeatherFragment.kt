package com.app.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.app.weatherapp.R
import com.app.weatherapp.adapter.WeatherDataAdapter
import com.app.weatherapp.databinding.FragmentWeatherBinding
import com.app.weatherapp.extensions.isInternetAvailable
import com.app.weatherapp.viewmodel.WeatherViewModel


class WeatherFragment : Fragment() {
    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherDataAdapter: WeatherDataAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        getWeatherData()
    }

    private fun init() {
        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)

    }

    private fun getWeatherData() {
        if (isInternetAvailable()) {
            weatherViewModel.fetchWeatherData().observe(this, { weatherData ->
                weatherData?.contactList?.let { weatherList ->
                    if (weatherList.isEmpty()) {
                        binding.recyclerWeather.visibility = View.GONE
                        showToast(resources.getString(R.string.error_msg))

                    } else {
                        binding.recyclerWeather.visibility = View.VISIBLE
                        weatherDataAdapter = WeatherDataAdapter(weatherList)
                        binding.recyclerWeather.adapter = weatherDataAdapter
                    }
                }

            })
        } else {
            showToast(resources.getString(R.string.error_msg))
        }


    }

    private fun showToast(message: String) {

        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

