package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId

typealias GetMovieDetails = (MovieId) -> MovieDetails
typealias InitializeMovieCatalog = suspend () -> MovieCatalog
typealias FetchImdbIds = () -> ImdbIds //port
typealias FetchMovieDetails = suspend (ImdbId) -> MovieDetails //port
typealias GenerateMovieId = (ImdbId) -> MovieId //port


suspend fun initializeMovieCatalog(
    fetchImdbIds: FetchImdbIds,
    fetchMovieDetails: FetchMovieDetails,
    generateMovieId: GenerateMovieId
) {
    fetchImdbIds().ids
        .associate { generateMovieId(it) to fetchMovieDetails(it) }
        .let { MovieCatalog(it) }
}

suspend fun getMovieDetails(initializeCatalog: InitializeMovieCatalog): GetMovieDetails =
    initializeCatalog()::getMovieDetails
