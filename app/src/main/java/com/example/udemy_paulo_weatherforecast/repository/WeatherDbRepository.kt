package com.example.udemy_paulo_weatherforecast.repository

import com.example.udemy_paulo_weatherforecast.data.WeatherDao
import com.example.udemy_paulo_weatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao : WeatherDao) {

    fun getFavorite() : Flow<List<Favorite>> = weatherDao.getFavorite()

    suspend fun getFavoriteByCity(city : String) : Favorite = weatherDao.getFavoriteByCity(city)

    suspend fun insertFavorite(favorite : Favorite) : Unit = weatherDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite : Favorite) : Unit = weatherDao.updateFavorite(favorite)

    suspend fun deleteAllFavorite() : Unit = weatherDao.deleteAllFavorite()

    suspend fun deleteFavorite(favorite : Favorite) : Unit = weatherDao.deleteFavorite(favorite)
}