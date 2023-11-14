package com.example.udemy_paulo_weatherforecast.repository

import com.example.udemy_paulo_weatherforecast.data.WeatherDao
import com.example.udemy_paulo_weatherforecast.model.Favorite
import com.example.udemy_paulo_weatherforecast.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorite(): Flow<List<Favorite>> = weatherDao.getFavorite()

    suspend fun getFavoriteByCity(city: String): Favorite = weatherDao.getFavoriteByCity(city)

    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)

    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)

    suspend fun deleteAllFavorite()= weatherDao.deleteAllFavorite()

    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)

    //Unit
    fun getUnit(): Flow<List<Unit>> = weatherDao.getUnit()

    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)

    suspend fun updateUnit(unit: Unit) = weatherDao.updateUnit(unit)

    suspend fun deleteAllUnit() = weatherDao.deleteAllUnit()

    suspend fun deleteUnit(unit: Unit) = weatherDao.deleteUnit(unit)
}