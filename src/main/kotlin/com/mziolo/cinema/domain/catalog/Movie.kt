package com.mziolo.cinema.domain.catalog

data class Movie(
    val movieId: MovieId,
    val name: String,
    val description: String,
    val releaseYear: Int,
    val imdbRating: Double,
    val runtime: Int
)