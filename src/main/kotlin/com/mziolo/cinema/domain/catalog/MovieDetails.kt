package com.mziolo.cinema.domain.catalog

data class MovieDetails(
    val name: String,
    val description: String,
    val releaseYear: Int,
    val imdbRating: Double,
    val runtimeInMinutes: Int
)