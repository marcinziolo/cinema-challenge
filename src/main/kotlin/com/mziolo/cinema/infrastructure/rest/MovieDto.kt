package com.mziolo.cinema.infrastructure.rest

import java.util.UUID

data class MovieDto(
    val id: UUID,
    val name: String,
    val description: String,
    val releaseYear: Int,
    val imdbRating: Double,
    val userRating: String,
    val runtimeInMinutes: Int
)