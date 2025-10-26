package com.example.vijayiproject.presentation.states

import com.example.vijayiproject.data.dto.movieData

data class MovieUiState(
    val isLoading: Boolean = false,
    val upcomingMovies: List<movieData> = emptyList(),
    val popularMovies: List<movieData> = emptyList(),
    val error: String = "",
)
