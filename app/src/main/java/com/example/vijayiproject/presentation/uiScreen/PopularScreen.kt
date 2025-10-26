package com.example.vijayiproject.presentation.uiScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.navigation.NavHostController
import com.example.vijayiproject.presentation.viewModel.MainViewModel
import com.example.vijayiproject.presentation.components.AppbarComponent
import com.example.vijayiproject.presentation.components.LoadingScreen
import com.example.vijayiproject.presentation.components.MovieItem
import com.example.vijayiproject.presentation.states.MovieUiState

@Composable
fun PopularScreen(
    navController: NavHostController,
    uiState: MovieUiState,
    viewModel: MainViewModel,
) {


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
    ) {
        AppbarComponent("Popular Movies")

        if (uiState.isLoading) LoadingScreen(modifier = Modifier)

        else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
            ) {
                items(uiState.popularMovies.size) {

                    MovieItem(uiState.popularMovies[it], navController, viewModel)
                }
            }
        }
    }
}

