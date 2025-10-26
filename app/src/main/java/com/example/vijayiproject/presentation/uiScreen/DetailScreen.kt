package com.example.vijayiproject.presentation.uiScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.vijayiproject.presentation.viewModel.MainViewModel
import com.example.vijayiproject.presentation.components.AppbarComponent
import com.example.vijayiproject.utils.Constants.IMAGE_BASE_URL

@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    val data = viewModel.selectedMovie.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary,
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        AppbarComponent(title = "Movie Details")
        BackdropImageSection(
            imageUrl = IMAGE_BASE_URL + data?.backdrop_path,
            title = data?.title
        )

        Spacer(modifier = Modifier.height(16.dp))

        PosterAndInfoSection(
            posterUrl = IMAGE_BASE_URL + data?.poster_path,
            title = data?.title ?: "",
            rating = data?.vote_average ?: 0.0,
            language = data?.original_language ?: "",
            releaseDate = data?.release_date ?: ""
        )

        Spacer(modifier = Modifier.height(16.dp))

        MovieOverviewSection(overview = data?.overview ?: "")
    }
}
@Composable
fun BackdropImageSection(
    imageUrl: String?,
    title: String?
) {
    val painterState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .build()
    ).state

    when (painterState) {
        is AsyncImagePainter.State.Success -> {
            Image(
                painter = painterState.painter,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )
        }

        is AsyncImagePainter.State.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = title,
                    modifier = Modifier.size(70.dp)
                )
            }
        }

        else -> Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@Composable
fun PosterAndInfoSection(
    posterUrl: String?,
    title: String,
    rating: Double,
    language: String,
    releaseDate: String
) {
    val painterState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(posterUrl)
            .size(Size.ORIGINAL)
            .build()
    ).state

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Box(
            modifier = Modifier
                .width(160.dp)
                .height(240.dp)
        ) {
            when (painterState) {
                is AsyncImagePainter.State.Success -> {
                    Image(
                        painter = painterState.painter,
                        contentDescription = title,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ImageNotSupported,
                            contentDescription = title,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))


        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "⭐ ${rating.toString().take(3)}",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Language: $language",
                color = Color.LightGray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Release: $releaseDate",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun MovieOverviewSection(overview: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Overview",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = overview,
            fontSize = 15.sp,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}
