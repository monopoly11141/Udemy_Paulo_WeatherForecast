package com.example.udemy_paulo_weatherforecast.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.udemy_paulo_weatherforecast.R
import com.example.udemy_paulo_weatherforecast.model.WeatherItem
import com.example.udemy_paulo_weatherforecast.util.formatDate
import com.example.udemy_paulo_weatherforecast.util.formatDateTime
import com.example.udemy_paulo_weatherforecast.util.formatDecimals


@Composable
fun HumidityWindPressureRow(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity icon",
                modifier = Modifier
                    .size(20.dp)
            )

            Text(
                text = "${weather.humidity}%",
                style = MaterialTheme.typography.labelSmall
            )
        }

        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure icon",
                modifier = Modifier
                    .size(20.dp)
            )

            Text(
                text = "${weather.pressure} psi",
                style = MaterialTheme.typography.labelSmall
            )
        }

        Row(
            modifier = Modifier
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind icon",
                modifier = Modifier
                    .size(20.dp)
            )

            Text(
                text = "${weather.clouds} mph",
                style = MaterialTheme.typography.labelSmall
            )
        }


    }
}

@Composable
fun SunsetSunrise(weather: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                bottom = 6.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(

        ) {
            Image(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise",
                modifier = Modifier
                    .size(30.dp)
            )

            Text(
                text = formatDateTime(weather.sunrise),
                style = MaterialTheme.typography.labelSmall
            )
        }

        Row(

        ) {
            Image(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset",
                modifier = Modifier
                    .size(30.dp)
            )

            Text(
                text = formatDateTime(weather.sunset),
                style = MaterialTheme.typography.labelSmall
            )
        }

    }
}

@Composable
fun WeatherDetailRow(weather: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(
            topEnd = CornerSize(6.dp)
        ),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDate(weather.dt).split(",")[0],
                modifier = Modifier
                    .padding(
                        start = 5.dp,

                        )
            )

            WeatherImage(imageUrl = imageUrl)

            Surface(
                modifier = Modifier
                    .padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFFFC400)
            ) {
                Text(
                    text = weather.weather[0].description,
                    modifier = Modifier
                        .padding(4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(formatDecimals(weather.temp.max) + "°")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color.LightGray
                        )
                    ) {
                        append(formatDecimals(weather.temp.min) + "°")
                    }

                }
            )
        }
    }
}

@Composable
fun WeatherImage(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "icon image",
        modifier = Modifier
            .size(80.dp)
    )

}
