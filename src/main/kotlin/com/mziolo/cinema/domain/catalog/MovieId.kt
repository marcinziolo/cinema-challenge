package com.mziolo.cinema.domain.catalog

import java.util.UUID

data class MovieId(
    val id: UUID
)

suspend fun <R> MovieId.validate(movieCatalog: MovieCatalog, delegate: suspend () -> R): R {
    if (!movieCatalog.contains(this)) throw InvalidMovieId
    return delegate()
}