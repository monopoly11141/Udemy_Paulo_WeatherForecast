package com.example.udemy_paulo_weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.udemy_paulo_weatherforecast.model.Favorite

@Database(
    entities = [Favorite::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun getWeatherDao() : WeatherDao
}