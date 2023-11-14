package com.example.udemy_paulo_weatherforecast.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.udemy_paulo_weatherforecast.model.Unit
import com.example.udemy_paulo_weatherforecast.widget.WeatherAppBar

@Composable
fun SettingScreen(
    navController: NavController,
    settingScreenViewModel: SettingScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                title = "Setting",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                navController = navController,
                isMainScreen = false
            ) {
                navController.popBackStack()
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            var isUnitToggled by remember { mutableStateOf(false) }
            val measurementUnitList = listOf("Imperial (F)", "Metric (C)")

            var choiceListFromDb = settingScreenViewModel.unitList.collectAsState().value

            val defaultChoice = if (choiceListFromDb.isEmpty()) {
                measurementUnitList[1]
            } else {
                choiceListFromDb[0].unit
            }
            var measurementUnit by remember { mutableStateOf(defaultChoice) }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change Units of Measurements",
                    modifier = Modifier
                        .padding(
                            bottom = 15.dp
                        )
                )

                IconToggleButton(
                    checked = !isUnitToggled,
                    onCheckedChange = { isChecked ->
                        isUnitToggled = !isChecked

                        measurementUnit = if (isUnitToggled) {
                            measurementUnitList[0]
                        } else {
                            measurementUnitList[1]
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color.Magenta.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = if (isUnitToggled) {
                            "Fahrenheit °F"
                        } else {
                            "Celsius °C"
                        }
                    )
                }

                Button(
                    onClick = {
                        settingScreenViewModel.deleteAllUnit()
                        settingScreenViewModel.insertUnit(
                            Unit(
                                unit = measurementUnit
                            )
                        )
                    },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEFBE42)
                    )
                ) {
                    Text(
                        text = "Save",
                        modifier = Modifier
                            .padding(4.dp),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

            }
        }
    }
}