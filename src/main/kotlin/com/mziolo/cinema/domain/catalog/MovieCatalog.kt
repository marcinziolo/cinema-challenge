package com.mziolo.cinema.domain.catalog

data class MovieCatalog(
    val catalog: Map<MovieId, Movie>
) {

    fun validateMovieId(movieId: MovieId) {
        if (!catalog.containsKey(movieId)) throw InvalidMovieId
    }

    fun getMovie(movieId: MovieId) = catalog[movieId]!!

    fun getMovies() = catalog.values
}