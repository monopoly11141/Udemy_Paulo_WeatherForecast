package com.example.udemy_paulo_weatherforecast.navigation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.udemy_paulo_weatherforecast.screen.main.MainScreen
import com.example.udemy_paulo_weatherforecast.screen.main.MainScreenViewModel
import com.example.udemy_paulo_weatherforecast.screen.search.SearchScreen
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

        val route = WeatherScreen.MainScreen.name
        composable(
            route = "$route/{city}",
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments!!.getString("city").let { city ->
                val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
                MainScreen(
                    navController = navController,
                    mainScreenViewModel = mainScreenViewModel,
                    city = city
                )
            }
        }

        composable(route = WeatherScreen.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

    }

}