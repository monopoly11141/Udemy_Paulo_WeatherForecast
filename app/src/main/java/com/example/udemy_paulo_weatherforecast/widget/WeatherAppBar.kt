package com.example.udemy_paulo_weatherforecast.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.udemy_paulo_weatherforecast.navigation.WeatherScreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    navController: NavController? = null,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {},
) {
    val isDialogShowing = remember { mutableStateOf(false) }

    if (isDialogShowing.value) {
        ShowSettingDropDownMenu(
            isDialogShowing = isDialogShowing,
            navController = navController
        )
    }

    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon"
                    )
                }

                IconButton(onClick = {
                    isDialogShowing.value = true
                }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "search icon"
                    )
                }
            } else {
                Box() {

                }
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "null",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable {
                            onButtonClicked.invoke()
                        }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
fun ShowSettingDropDownMenu(isDialogShowing: MutableState<Boolean>, navController: NavController?) {
    var isExpanded by remember { mutableStateOf(true) }

    val dropDownMenuItemList = listOf(
        "About",
        "Favorites",
        "Settings"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(
                top = 50.dp,
                right = 20.dp
            )
    ) {
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            modifier = Modifier
                .width(150.dp)
                .background(Color.White)
        ) {
            dropDownMenuItemList.forEachIndexed { index, text ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        isDialogShowing.value = false
                    },

                    leadingIcon = {
                        Icon(
                            imageVector = when (text) {
                                "About" -> Icons.Default.Info
                                "Favorites" -> Icons.Default.FavoriteBorder
                                else -> Icons.Default.Settings
                            },
                            contentDescription = "",
                            tint = Color.LightGray
                        )
                    },

                    text = {
                        Text(
                            text = text,
                            modifier = Modifier
                                .clickable {
                                    navController?.navigate(
                                        when (text) {
                                            "About" -> WeatherScreen.AboutScreen.name
                                            "Favorites" -> WeatherScreen.FavoriteScreen.name
                                            else -> WeatherScreen.SettingScreen.name
                                        }
                                    )
                                },
                            fontWeight = FontWeight.Light
                        )
                    }
                )
            }

        }
    }

}
