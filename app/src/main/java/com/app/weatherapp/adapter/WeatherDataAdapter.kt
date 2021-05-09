package com.app.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.weatherapp.R
import com.app.weatherapp.databinding.RowWeatherDataBinding
import com.app.weatherapp.extensions.convertDateToFormat
import com.app.weatherapp.model.Weather

class WeatherDataAdapter(private var weatherDataList: List<Weather>) :
    RecyclerView.Adapter<WeatherDataAdapter.WeatherDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDataViewHolder {
        val binding = RowWeatherDataBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherDataViewHolder, position: Int) {
        with(holder) {
            val temperature = String.format(
                binding.root.context.resources.getString(R.string.str_temperature),
                weatherDataList[position].temperature
            )
            val rain = "${weatherDataList[position].rain}%"
            val wind = String.format(
                binding.root.context.resources.getString(R.string.str_wind),
                weatherDataList[position].wind
            )
            val time = weatherDataList[position].time.convertDateToFormat(DATE_FORMAT)
            this.binding.tvTemperature.text = temperature
            this.binding.tvRain.text = rain
            this.binding.tvWind.text = wind
            this.binding.tvTime.text= time

        }
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    class WeatherDataViewHolder(val binding: RowWeatherDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val DATE_FORMAT = "MMMM dd yyyy"
    }
}



