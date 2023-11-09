package com.example.udemy_paulo_weatherforecast.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.udemy_paulo_weatherforecast.data.DataOrException
import com.example.udemy_paulo_weatherforecast.model.Weather
import com.example.udemy_paulo_weatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getWeather(cityQuery: String): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            weatherApi.getWeather(query = cityQuery)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        Log.d(TAG, "getWeather outside catch: ${response}")
        return DataOrException(data = response, isLoading = false)
    }


}