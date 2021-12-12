package com.mziolo.cinema.infrastructure

import com.mziolo.cinema.domain.catalog.FetchImdbIds
import com.mziolo.cinema.domain.catalog.FetchMovie
import com.mziolo.cinema.domain.catalog.GenerateMovieId
import com.mziolo.cinema.domain.catalog.InitializeMovieCatalog
import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.domain.catalog.initializeMovieCatalogPrototype
import com.mziolo.cinema.domain.showtime.FetchShowTimes
import com.mziolo.cinema.domain.showtime.SaveShowTime
import com.mziolo.cinema.domain.showtime.UpdateShowTime
import com.mziolo.cinema.domain.showtime.updateShowTimePrototype
import com.mziolo.cinema.infrastructure.catalog.FetchImdbIdsAdapter
import com.mziolo.cinema.infrastructure.catalog.FetchMovieAdapter
import com.mziolo.cinema.infrastructure.catalog.GenerateMovieIdAdapter
import com.mziolo.cinema.infrastructure.showtime.FetchShowTimesAdapter
import com.mziolo.cinema.infrastructure.showtime.SaveShowTimeAdapter
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainConfiguration {

    @Bean
    fun initializeMovieCatalog(
        fetchImdbIds: FetchImdbIdsAdapter,
        fetchMovie: FetchMovieAdapter,
        generateMovieId: GenerateMovieIdAdapter
    ) = runBlocking { initializeMovieCatalogPrototype(fetchImdbIds, fetchMovie, generateMovieId) }

    @Bean
    fun movieCatalog(inititializeMovieCatalog: InitializeMovieCatalog) = runBlocking { inititializeMovieCatalog() }

    @Bean
    fun updateShowTime(
        fetchShowTimes: FetchShowTimesAdapter,
        saveShowTime: SaveShowTimeAdapter,
    ): UpdateShowTime = updateShowTimePrototype(fetchShowTimes, saveShowTime)

}