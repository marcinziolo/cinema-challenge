package com.mziolo.cinema.domain

import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.domain.catalog.validate
import com.mziolo.cinema.domain.rating.MovieVote
import com.mziolo.cinema.domain.rating.RateMovie

class RatingFacade(
    private val _rateMovie: RateMovie,
    private val movieCatalog: MovieCatalog
) {
    suspend fun rateMovie(movieVote: MovieVote) {
        movieVote.movieId.validate(movieCatalog) {
            _rateMovie(movieVote)
        }
    }
}