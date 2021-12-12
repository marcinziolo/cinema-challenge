package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.catalog.MovieCatalog

typealias SaveShowTime = suspend (ShowTime) -> Unit //port

typealias FetchShowTimes = suspend (ShowTimeDate) -> ShowTimes //port

typealias UpdateShowTime = suspend (ShowTime) -> Unit

const val MINIMUM_BREAK_IN_MINUTES = 15

fun updateShowTimePrototype(
    fetchShowTimes: FetchShowTimes,
    saveShowTime: SaveShowTime,
): UpdateShowTime = { showTime ->
    val otherShowTimes = fetchShowTimes(showTime.date)
        .filter { it.showTimeId != showTime.showTimeId }

    if (otherShowTimes.any { it.overLap(showTime) })
        throw ShowTimeOverlapped
    saveShowTime(showTime)
}

fun FetchShowTimes.updateRuntime(
    movieCatalog: MovieCatalog
): FetchShowTimes = {
    this(it).map { showTime ->
        showTime.copy(runtime = movieCatalog.getMovie(showTime.movieId).runtimeInMinutes + MINIMUM_BREAK_IN_MINUTES)
    }
}


