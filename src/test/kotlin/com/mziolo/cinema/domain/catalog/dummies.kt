package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId
import java.util.UUID

internal val dummyMovieId = MovieId(UUID.fromString("f3a47ecd-d73a-44a7-94ab-305d4d78e353"))
internal val dummyMovieDetails = MovieDetails(
    name = "Fast&Furious",
    description = "Los Angeles police officer Brian O'Conner...",
    releaseYear = 2001,
    imdbRating = 7.6,
    runtimeInMinutes = 106
)
internal val dummyCatalog = MovieCatalog(mapOf(dummyMovieId to dummyMovieDetails))

internal val dummyImdbId = ImdbId("t21213")
internal val dummyImdbIds = ImdbIds(listOf(ImdbId("t21213")))