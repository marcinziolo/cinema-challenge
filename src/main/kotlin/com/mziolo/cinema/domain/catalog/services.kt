package com.mziolo.cinema.domain.catalog

typealias InitializeMovieCatalog = suspend () -> MovieCatalog

typealias FetchImdbIds = () -> ImdbIds //port
typealias FetchMovie = suspend (ImdbId, MovieId) -> Movie //port
typealias GenerateMovieId = (ImdbId) -> MovieId //port


suspend fun initializeMovieCatalogPrototype(
    fetchImdbIds: FetchImdbIds,
    fetchMovie: FetchMovie,
    generateMovieId: GenerateMovieId
): InitializeMovieCatalog = {
    fetchImdbIds().ids
        .associate {
            val movieId = generateMovieId(it)
            movieId to fetchMovie(it, movieId)
        }
        .let { MovieCatalog(it) }
}