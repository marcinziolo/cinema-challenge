package com.mziolo.cinema.domain.rating

import com.mziolo.cinema.domain.core.MovieId

data class MovieVote(
    val movieId: MovieId,
    val value: Int
)
