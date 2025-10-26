package com.example.vijayiproject.data.dto

data class MovieDto(
    val dates: Dates,
    val page: Int,
    val results: List<movieData>,
    val total_pages: Int,
    val total_results: Int
)