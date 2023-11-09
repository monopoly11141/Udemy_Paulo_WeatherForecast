package com.example.udemy_paulo_weatherforecast.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.udemy_paulo_weatherforecast.data.DataOrException
import com.example.udemy_paulo_weatherforecast.model.Weather
import com.example.udemy_paulo_weatherforecast.widget.WeatherAppBar

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)
    ) {
        value = mainScreenViewModel.getWeather(city = "Seattle")
    }.value

    if (weatherData.isLoading == true) {
        CircularProgressIndicator()
    } else if (weatherData.isLoading != null) {
        MainScaffold(weather = weatherData.data!!, navController)
    }
}

@Composable
fun MainScaffold(weather: Weather, navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar()
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            MainContent(data = weather)
        }

    }

}

@Composable
fun MainContent(data: Weather) {
    Text(text = data.city.name)
}
