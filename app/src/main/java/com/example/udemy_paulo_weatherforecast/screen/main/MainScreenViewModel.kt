package com.example.udemy_paulo_weatherforecast.screen.main

import androidx.lifecycle.ViewModel
import com.example.udemy_paulo_weatherforecast.data.DataOrException
import com.example.udemy_paulo_weatherforecast.model.Weather
import com.example.udemy_paulo_weatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel() {

    suspend fun getWeather(city: String): DataOrException<Weather, Boolean, Exception> {
        return weatherRepository.getWeather(cityQuery = city)
    }

}