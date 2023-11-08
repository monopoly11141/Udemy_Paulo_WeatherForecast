package com.example.udemy_paulo_weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.udemy_paulo_weatherforecast.screen.main.MainScreen
import com.example.udemy_paulo_weatherforecast.screen.splash.WeatherSplashScreen

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
            MainScreen(navController = navController)
        }

    }

}