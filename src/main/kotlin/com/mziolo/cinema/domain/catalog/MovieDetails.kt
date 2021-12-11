package com.mziolo.cinema.domain.catalog

import java.time.LocalDate

data class MovieDetails(
    val name: String,
    val description: String,
    val releaseDate: LocalDate,
    val imdbRating: Double,
    val runtime: Int
)