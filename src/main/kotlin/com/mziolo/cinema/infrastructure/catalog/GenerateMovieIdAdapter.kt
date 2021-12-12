package com.mziolo.cinema.infrastructure.catalog

import com.mziolo.cinema.domain.catalog.GenerateMovieId
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.MovieId
import java.util.UUID

class GenerateMovieIdAdapter: GenerateMovieId {
    override fun invoke(imdbId: ImdbId): MovieId = UUID.nameUUIDFromBytes(imdbId.id.toByteArray()).let { MovieId(it) }
}