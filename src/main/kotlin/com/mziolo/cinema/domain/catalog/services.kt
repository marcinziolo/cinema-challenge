package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId

typealias GetMovieDetails = (MovieId) -> MovieDetails
typealias InitializeMovieCatalog = suspend () -> MovieCatalog
typealias FetchImdbIds = () -> ImdbIds //port
typealias FetchMovieDetails = suspend (ImdbId) -> MovieDetails //port
typealias GenerateMovieId = (ImdbId) -> MovieId //port


suspend fun initializeMovieCatalogPrototype(
    fetchImdbIds: FetchImdbIds,
    fetchMovieDetails: FetchMovieDetails,
    generateMovieId: GenerateMovieId
): InitializeMovieCatalog {
    return {
        fetchImdbIds().ids
            .associate { generateMovieId(it) to fetchMovieDetails(it) }
            .let { MovieCatalog(it) }
    }
}

suspend fun getMovieDetailsPrototype(initializeCatalog: InitializeMovieCatalog): GetMovieDetails {
    val catalog = initializeCatalog()
    return { movieId: MovieId -> catalog.getMovieDetails(movieId) }
}
