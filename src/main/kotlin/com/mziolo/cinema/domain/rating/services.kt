package com.mziolo.cinema.domain.rating

import com.mziolo.cinema.domain.core.MovieId

typealias RateMovie = (MovieVote) -> Unit
typealias GetRatings = (MovieId) -> RatingValue