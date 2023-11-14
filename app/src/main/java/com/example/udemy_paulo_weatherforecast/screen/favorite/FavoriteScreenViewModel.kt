package com.example.udemy_paulo_weatherforecast.screen.favorite

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemy_paulo_weatherforecast.model.Favorite
import com.example.udemy_paulo_weatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(private val weatherDbRepository: WeatherDbRepository) : ViewModel() {
    private val _favoriteList = MutableStateFlow<List<Favorite>>(emptyList())
    val favoriteList = _favoriteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherDbRepository.getFavorite().distinctUntilChanged()
                .collect { listOfFavorite ->
                    if (listOfFavorite.isEmpty()) {
                        Log.d(TAG, "Empty favorite ")
                    } else {
                        _favoriteList.value = listOfFavorite
                        Log.d(TAG, "${favoriteList.value}")
                    }
                }
        }
    }

    fun insertFavorite(favorite : Favorite) = viewModelScope.launch {
        weatherDbRepository.insertFavorite(favorite)
    }

    fun updateFavorite(favorite: Favorite) = viewModelScope.launch {
        weatherDbRepository.updateFavorite(favorite)
    }

    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
        weatherDbRepository.deleteFavorite(favorite)
    }
}