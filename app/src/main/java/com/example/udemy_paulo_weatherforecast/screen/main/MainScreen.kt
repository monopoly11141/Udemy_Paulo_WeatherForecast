package com.example.udemy_paulo_weatherforecast.screen.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.udemy_paulo_weatherforecast.data.DataOrException
import com.example.udemy_paulo_weatherforecast.model.Weather
import com.example.udemy_paulo_weatherforecast.model.WeatherItem
import com.example.udemy_paulo_weatherforecast.navigation.WeatherScreen
import com.example.udemy_paulo_weatherforecast.util.formatDate
import com.example.udemy_paulo_weatherforecast.util.formatDecimals
import com.example.udemy_paulo_weatherforecast.widget.*

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    city: String?
) {
    Log.d(TAG, "MainScreen: $city")

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(isLoading = true)
    ) {
        value = mainScreenViewModel.getWeather(city = "$city")
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
            Surface(shadowElevation = 5.dp) {
                WeatherAppBar(
                    title = "${weather.city.name}, ${weather.city.country}",
                    navController = navController,
                    onAddActionClicked = {
                        navController.navigate(WeatherScreen.SearchScreen.name)
                    }
                ) {
                    Log.d(TAG, "MainScaffold: Button Clicked")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            MainContent(weather = weather)
        }

    }

}

@Composable
fun MainContent(weather: Weather) {

    val firstWeatherItem = weather.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${firstWeatherItem.weather[0].icon}.png"

    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(firstWeatherItem.dt),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //Image
                WeatherImage(
                    imageUrl = imageUrl
                )

                Text(
                    text = formatDecimals(firstWeatherItem.temp.day) + "°",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = firstWeatherItem.weather[0].main,
                    fontStyle = FontStyle.Italic
                )

            }
        }

        HumidityWindPressureRow(weather = firstWeatherItem)

        HorizontalDivider()

        SunsetSunrise(weather = firstWeatherItem)

        Text(
            text = "This week",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = weather.list) { weatherItem: WeatherItem ->
                    WeatherDetailRow(weather = weatherItem)
                }
            }
        }
    }

}
