package com.mziolo.cinema.infrastructure.catalog.adapter

import com.mziolo.cinema.domain.catalog.FetchImdbIds
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.ImdbIds
import com.mziolo.cinema.infrastructure.catalog.ImdbMoviesProperties
import org.springframework.stereotype.Component

@Component
class FetchImdbIdsAdapter(
//    @Value("\${app.imdbMovieIds}") val movieIds: List<String>
    val imdbMoviesProperties: ImdbMoviesProperties
): FetchImdbIds {
    override fun invoke(): ImdbIds = ImdbIds(imdbMoviesProperties.imdbMovieIds.map { ImdbId(it) })
}