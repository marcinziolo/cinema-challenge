package com.mziolo.cinema.domain

import com.mziolo.cinema.domain.catalog.MovieCatalog
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
        val movieId = showTime.movieId
        val movie = movieCatalog.getMovie(movieId)
        movieCatalog contains movieId then {
            showTime runtimeIsEnoughLongerThan movie then {
                _updateShowTime(showTime)
            }
        }
    }

    suspend fun fetchShowTimes(showTimeDate: ShowTimeDate) = _fetchShowTimes(showTimeDate)
}