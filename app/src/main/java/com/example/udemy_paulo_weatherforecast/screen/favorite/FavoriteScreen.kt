package com.example.udemy_paulo_weatherforecast.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.udemy_paulo_weatherforecast.model.Favorite
import com.example.udemy_paulo_weatherforecast.navigation.WeatherScreen
import com.example.udemy_paulo_weatherforecast.widget.WeatherAppBar

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Favorite Cities",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                navController = navController
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val favoriteList = favoriteScreenViewModel.favoriteList.collectAsState().value
                LazyColumn {

                    items(
                        items = favoriteList
                    ) {
                        CityRow(favorite = it, navController = navController, favoriteScreenViewModel)
                    }
                }

            }
        }
    }
}

@Composable
fun CityRow(favorite: Favorite, navController: NavController, favoriteScreenViewModel: FavoriteScreenViewModel) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreen.MainScreen.name + "/${favorite.city}")
            },
        shape = CircleShape.copy(
            topEnd = CornerSize(6.dp),
        ),
        color = Color(0xFFB2DFDB)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = favorite.city,
                modifier = Modifier
                    .padding(
                        start = 4.dp
                    )
            )

            Surface(
                modifier = Modifier
                    .padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            ) {
                Text(
                    text = favorite.country,
                    modifier = Modifier
                        .padding(4.dp),
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "delete",
                modifier = Modifier
                    .clickable {
                        favoriteScreenViewModel.deleteFavorite(favorite)
                    },
                tint = Color.Red.copy(alpha = 0.3f)
            )
        }
    }
}
