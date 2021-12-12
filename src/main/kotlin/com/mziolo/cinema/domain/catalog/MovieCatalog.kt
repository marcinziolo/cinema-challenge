package com.mziolo.cinema.domain.catalog

data class MovieCatalog(
    val catalog: Map<MovieId, Movie>
) {

    fun contains(movieId: MovieId) = catalog.containsKey(movieId)

    fun getMovie(movieId: MovieId) = catalog[movieId]!!

    fun getMovies() = catalog.values
}