package com.mziolo.cinema.domain.rating

import com.mziolo.cinema.domain.catalog.MovieId

typealias RateMovie = suspend (MovieVote) -> Unit
typealias GetRatings = suspend (MovieId) -> Rating