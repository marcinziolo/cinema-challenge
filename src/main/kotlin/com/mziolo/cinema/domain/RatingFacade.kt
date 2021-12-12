package com.mziolo.cinema.domain

import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.domain.rating.MovieVote
import com.mziolo.cinema.domain.rating.RateMovie

class RatingFacade(
    private val rateMovie: RateMovie,
    private val movieCatalog: MovieCatalog
) {
    suspend fun voteMovie(movieVote: MovieVote) {
        movieCatalog.validateMovieId(movieVote.movieId)
        rateMovie(movieVote)
    }
}