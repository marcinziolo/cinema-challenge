package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId

typealias GetMovieDetails = (MovieId) -> MovieDetails
typealias InitializeMovieCatalog = () -> MovieCatalog //port

fun getMovieDetails(initializeCatalog: InitializeMovieCatalog): GetMovieDetails = initializeCatalog()::getMovieDetails
