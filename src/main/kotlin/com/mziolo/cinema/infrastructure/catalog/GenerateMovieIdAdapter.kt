package com.mziolo.cinema.infrastructure.catalog

import com.mziolo.cinema.domain.catalog.GenerateMovieId
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.MovieId
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateMovieIdAdapter : GenerateMovieId {
    override fun invoke(imdbId: ImdbId): MovieId = UUID.nameUUIDFromBytes(imdbId.id.toByteArray()).let { MovieId(it) }
}