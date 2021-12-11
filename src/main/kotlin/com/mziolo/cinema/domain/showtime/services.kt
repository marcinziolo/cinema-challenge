package com.mziolo.cinema.domain.showtime

typealias SaveShowTime = suspend (ShowTime) -> Unit //port
typealias FetchShowTimes = suspend (ShowTimeDate) -> ShowTimes //port
typealias UpdateShowTime = suspend (ShowTime) -> Unit

const val MINIMUM_BREAK_IN_MINUTES = 15L

fun updateShowTimePrototype(
    fetchShowTimes: FetchShowTimes,
    saveShowTime: SaveShowTime
): UpdateShowTime = { showTime ->
    if (fetchShowTimes(showTime.date).any { it.overLap(showTime) })
        throw ShowTimeOverlap
    saveShowTime(showTime)
}

