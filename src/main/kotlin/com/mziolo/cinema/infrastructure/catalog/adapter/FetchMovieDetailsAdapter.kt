package com.mziolo.cinema.infrastructure.catalog.adapter

import com.mziolo.cinema.domain.catalog.FetchMovieDetails
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.MovieDetails
import com.mziolo.cinema.infrastructure.catalog.ImdbMovieDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Component
class FetchMovieDetailsAdapter(
    @Value("\${app.imdbHost}") private val imdbHost: String,
    @Value("\${app.imdbKey}") private val imdbKey: String,
    private val webClient: WebClient
): FetchMovieDetails {

    override suspend fun invoke(imdbId: ImdbId): MovieDetails {
        return webClient.get().uri("$imdbHost?apikey=$imdbKey&i=tt0232500")
            .accept(APPLICATION_JSON)
            .retrieve()
            .awaitBody<ImdbMovieDto>()
            .toMovieDetails()
    }
}

private fun ImdbMovieDto.toMovieDetails(): MovieDetails {
    val imdbSource = "Internet Movie Database"
    val valueRegex = """([0-9]{1,2}.[0-9]{1,2})/10""".toRegex()

    return MovieDetails(
        name = title,
        description = plot,
        releaseYear = year.toInt(),
        imdbRating = ratings
            .find { it.source == imdbSource }
            .let { valueRegex.find(it!!.value) }
            .let { it!!.groupValues[1].toDouble()},
        runtime = runtime
    )
}