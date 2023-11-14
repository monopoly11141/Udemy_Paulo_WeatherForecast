package com.example.udemy_paulo_weatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.udemy_paulo_weatherforecast.model.Favorite
import  com.example.udemy_paulo_weatherforecast.model.Unit

@Database(
    entities = [Favorite::class, Unit::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun getWeatherDao() : WeatherDao
}