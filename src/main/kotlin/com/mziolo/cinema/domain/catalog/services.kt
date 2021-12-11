package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId

typealias GetMovieDetails = (MovieId) -> MovieDetails
typealias InitializeMovieCatalog = suspend () -> MovieCatalog
typealias FetchImdbMovieIds = () -> ImdbMovieIds //port
typealias FetchMovieDetails = suspend (ImdbMovieId) -> MovieDetails //port
typealias GenerateMovieId = (ImdbMovieId) -> MovieId //port


suspend fun initializeMovieCatalog(
    fetchImdbMovieIds: FetchImdbMovieIds,
    fetchMovieDetails: FetchMovieDetails,
    generateMovieId: GenerateMovieId
) {
    fetchImdbMovieIds().ids
        .associate { generateMovieId(it) to fetchMovieDetails(it) }
        .let { MovieCatalog(it) }
}

suspend fun getMovieDetails(initializeCatalog: InitializeMovieCatalog): GetMovieDetails =
    initializeCatalog()::getMovieDetails
