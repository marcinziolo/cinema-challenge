package com.mziolo.cinema.domain

import com.mziolo.cinema.domain.catalog.Movie
import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.domain.catalog.MovieId
import com.mziolo.cinema.domain.rating.GetRatings
import com.mziolo.cinema.domain.rating.Rating

data class MovieWithRating(
    val movie: Movie,
    val rating: Rating
)

class MovieFacade(
    private val movieCatalog: MovieCatalog,
    private val getRatings: GetRatings
) {

    suspend fun getMovie(movieId: MovieId): MovieWithRating {
        movieCatalog.validateMovieId(movieId)
        return movieCatalog.getMovie(movieId).let { MovieWithRating(it, getRatings(movieId)) }
    }

    suspend fun getAllMovies(): Collection<MovieWithRating> {
        return movieCatalog.getMovies().map {
            MovieWithRating(it, getRatings(it.movieId))
        }
    }
}