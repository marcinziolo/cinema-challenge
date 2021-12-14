package com.mziolo.cinema.infrastructure

import com.mziolo.cinema.domain.MovieFacade
import com.mziolo.cinema.domain.RatingFacade
import com.mziolo.cinema.domain.ShowTimeFacade
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
    ) = MovieFacade(movieCatalog, getRatings)

    @Bean
    fun ratingFlow(
        rateMovie: RateMovieAdapter,
        movieCatalog: MovieCatalog
    ) = RatingFacade(rateMovie, movieCatalog)

    @Bean
    fun showTimeFlow(
        updateShowTime: UpdateShowTime,
        fetchShowTimes: FetchShowTimesAdapter,
        movieCatalog: MovieCatalog
    ) = ShowTimeFacade(updateShowTime, fetchShowTimes, movieCatalog)
}