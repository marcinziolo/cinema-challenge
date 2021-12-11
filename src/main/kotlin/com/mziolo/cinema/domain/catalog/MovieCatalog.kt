package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId

data class MovieCatalog(
    val catalog: Map<MovieId, MovieDetails>
)

fun MovieCatalog.getMovieDetails(movieId: MovieId): MovieDetails = catalog[movieId]!!