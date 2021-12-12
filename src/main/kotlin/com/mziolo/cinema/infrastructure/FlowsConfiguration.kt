package com.mziolo.cinema.infrastructure

import com.mziolo.cinema.domain.MovieFlow
import com.mziolo.cinema.domain.RatingFlow
import com.mziolo.cinema.domain.ShowTimeFlow
import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.domain.showtime.UpdateShowTime
import com.mziolo.cinema.infrastructure.rating.GetRatingsAdapter
import com.mziolo.cinema.infrastructure.rating.RateMovieAdapter
import com.mziolo.cinema.infrastructure.showtime.FetchShowTimesAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlowsConfiguration {

    @Bean
    fun movieFlow(
        getRatings: GetRatingsAdapter,
        movieCatalog: MovieCatalog
    ) = MovieFlow(movieCatalog, getRatings)

    @Bean
    fun ratingFlow(
        rateMovie: RateMovieAdapter,
        movieCatalog: MovieCatalog
    ) = RatingFlow(rateMovie, movieCatalog)

    @Bean
    fun showTimeFlow(
        updateShowTime: UpdateShowTime,
        fetchShowTimes: FetchShowTimesAdapter,
        movieCatalog: MovieCatalog
    ) = ShowTimeFlow(updateShowTime, fetchShowTimes, movieCatalog)
}