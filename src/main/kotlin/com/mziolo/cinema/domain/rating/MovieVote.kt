package com.mziolo.cinema.domain.rating

import com.mziolo.cinema.domain.catalog.MovieId

data class MovieVote(
    val movieId: MovieId,
    val value: Int
)
