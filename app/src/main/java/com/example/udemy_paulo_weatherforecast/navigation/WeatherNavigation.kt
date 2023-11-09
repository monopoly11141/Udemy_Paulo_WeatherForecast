package com.example.udemy_paulo_weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.udemy_paulo_weatherforecast.screen.main.MainScreen
import com.example.udemy_paulo_weatherforecast.screen.main.MainScreenViewModel
import com.example.udemy_paulo_weatherforecast.screen.splash.WeatherSplashScreen
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreen.SplashScreen.name
    ) {
        composable(route = WeatherScreen.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

        composable(route = WeatherScreen.MainScreen.name) {
            val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
            MainScreen(navController = navController, mainScreenViewModel)
        }

    }

}