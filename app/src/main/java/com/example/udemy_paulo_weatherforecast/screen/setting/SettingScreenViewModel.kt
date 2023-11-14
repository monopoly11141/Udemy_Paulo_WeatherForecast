package com.example.udemy_paulo_weatherforecast.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemy_paulo_weatherforecast.model.Unit
import com.example.udemy_paulo_weatherforecast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(private val weatherDbRepository: WeatherDbRepository) : ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherDbRepository.getUnit().distinctUntilChanged()
                .collect { listOfUnit ->
                    if (listOfUnit.isEmpty()) {

                    } else {
                        _unitList.value = listOfUnit
                    }
                }
        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch {
        weatherDbRepository.insertUnit(unit)
    }

    fun updateUnit(unit: Unit) = viewModelScope.launch {
        weatherDbRepository.updateUnit(unit)
    }

    fun deleteUnit(unit: Unit) = viewModelScope.launch {
        weatherDbRepository.deleteUnit(unit)
    }

    fun deleteAllUnit() = viewModelScope.launch {
        weatherDbRepository.deleteAllUnit()
    }

}