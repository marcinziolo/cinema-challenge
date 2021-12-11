package com.mziolo.cinema.infrastructure

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "app")
data class ImdbMovies(
    var movies: List<ImdbMovie> = emptyList()
)

data class ImdbMovie(
    var title: String = "",
    var imdbId: String = ""
)