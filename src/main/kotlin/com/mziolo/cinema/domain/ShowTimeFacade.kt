package com.mziolo.cinema.domain

import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.domain.catalog.validate
import com.mziolo.cinema.domain.showtime.FetchShowTimes
import com.mziolo.cinema.domain.showtime.ShowTime
import com.mziolo.cinema.domain.showtime.ShowTimeDate
import com.mziolo.cinema.domain.showtime.UpdateShowTime

class ShowTimeFacade(
    private val _updateShowTime: UpdateShowTime,
    private val _fetchShowTimes: FetchShowTimes,
    private val movieCatalog: MovieCatalog
) {
    suspend fun updateShowTime(showTime: ShowTime) {
        showTime.movieId.validate(movieCatalog) {
            val showTimeWithRuntime = showTime.copy(runtime = movieCatalog.getMovie(showTime.movieId).runtimeInMinutes)
            _updateShowTime(showTimeWithRuntime)
        }
    }

    suspend fun fetchShowTimes(showTimeDate: ShowTimeDate) = _fetchShowTimes(showTimeDate)
}