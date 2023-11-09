package com.example.udemy_paulo_weatherforecast.network

import com.example.udemy_paulo_weatherforecast.model.Weather
import com.example.udemy_paulo_weatherforecast.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constant.API_KEY
    ): Weather


}