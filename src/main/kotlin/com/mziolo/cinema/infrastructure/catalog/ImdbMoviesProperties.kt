package com.mziolo.cinema.infrastructure.catalog

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app")
data class ImdbMoviesProperties(
    var imdbMovieIds: List<String> = emptyList()
)