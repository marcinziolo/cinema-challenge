package com.mziolo.cinema.domain.catalog

data class MovieCatalog(
    val catalog: Map<MovieId, Movie>
) {
    infix fun contains(movieId: MovieId): ContainsStep {
        if (!catalog.containsKey(movieId)) throw InvalidMovieId
        return ContainsStep
    }

    fun getMovie(movieId: MovieId) = catalog[movieId]!!

    fun getMovies() = catalog.values

    object ContainsStep {
        suspend infix fun <R> then(delegate: suspend () -> R)  = delegate()
    }
}