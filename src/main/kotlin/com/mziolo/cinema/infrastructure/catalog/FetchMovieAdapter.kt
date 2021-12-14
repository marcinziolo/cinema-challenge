package com.mziolo.cinema.infrastructure.catalog

import com.mziolo.cinema.domain.catalog.FetchMovie
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.Movie
import com.mziolo.cinema.domain.catalog.MovieId
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

private const val imdbSource = "Internet Movie Database"
private const val minSuffix = " min"
private val valueRegex = """([0-9]{1,2}.[0-9]{1,2})/10""".toRegex()

@Component
class FetchMovieAdapter(
    @Value("\${app.imdbHost}") private val imdbHost: String,
    @Value("\${app.imdbKey}") private val imdbKey: String,
    private val webClient: WebClient
) : FetchMovie {

    override suspend fun invoke(imdbId: ImdbId, movieId: MovieId): Movie {
        return webClient.get().uri("$imdbHost?apikey=$imdbKey&i=${imdbId.id}")
            .accept(APPLICATION_JSON)
            .retrieve()
            .awaitBody<ImdbMovieDto>()
            .toMovie(movieId)
    }
}

private fun ImdbMovieDto.toMovie(movieId: MovieId): Movie = Movie(
    movieId = movieId,
    name = title,
    description = plot,
    releaseYear = year.toInt(),
    imdbRating = ratings
        .find { it.source == imdbSource }
        .let { valueRegex.find(it!!.value) }
        .let { it!!.groupValues[1].toDouble() },
    runtime = runtime.removeSuffix(minSuffix).toInt()
)