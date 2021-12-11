package com.mziolo.cinema.domain.rating

import com.mziolo.cinema.domain.catalog.MovieId

typealias RateMovie = (MovieVote) -> Unit
typealias GetRatings = (MovieId) -> RatingValue