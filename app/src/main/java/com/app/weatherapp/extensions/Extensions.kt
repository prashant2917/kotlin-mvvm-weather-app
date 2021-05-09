package com.app.weatherapp.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

import java.util.*

fun Long.convertDateToFormat(pattern: String): String {


    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Instant.ofEpochMilli(this * 1000)
            .atZone(ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern(pattern))
            .toString()
    } else {
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateFormat.format(Date(this * 1000)).toString()
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun Fragment.isInternetAvailable(): Boolean {

    val connectivityManager =
        this.activity?.baseContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {

                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {

                    return true
                }
            }
        }
    }
    return false

}