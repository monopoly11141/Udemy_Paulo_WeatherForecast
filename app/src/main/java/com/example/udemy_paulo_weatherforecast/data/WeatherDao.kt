package com.example.udemy_paulo_weatherforecast.data

import androidx.room.*
import com.example.udemy_paulo_weatherforecast.model.Favorite
import kotlinx.coroutines.flow.Flow
import com.example.udemy_paulo_weatherforecast.model.Unit

@Dao
interface WeatherDao {

    @Query("SELECT * from favorite_table")
    fun getFavorite(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_table where city = :city")
    suspend fun getFavoriteByCity(city: String): Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE from favorite_table")
    suspend fun deleteAllFavorite()

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    //Unit
    @Query("SELECT * from setting_table")
    fun getUnit() : Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit : Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(unit : Unit)

    @Query("DELETE from setting_table")
    suspend fun deleteAllUnit()

    @Delete
    suspend fun deleteUnit(unit : Unit)

}