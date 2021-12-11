package com.mziolo.cinema.domain.catalog

import java.util.UUID

data class MovieCatalog(
    val catalog: Map<MovieId, Movie>
) {

    fun createMovieId(maybeMovieId: UUID): MovieId {
        val movieId = MovieId(maybeMovieId)
        return if(catalog.containsKey(movieId)) movieId else throw InvalidMovieId
    }

    fun getMovie(movieId: MovieId) = catalog[movieId]!!
}