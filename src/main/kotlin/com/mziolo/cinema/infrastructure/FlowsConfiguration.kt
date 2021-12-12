package com.mziolo.cinema.infrastructure

import com.mziolo.cinema.domain.MovieFlow
import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.infrastructure.rating.GetRatingsAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlowsConfiguration {

    @Bean
    fun movieFlow(
        getRatings: GetRatingsAdapter,
        movieCatalog: MovieCatalog
    ) = MovieFlow(movieCatalog, getRatings)

}