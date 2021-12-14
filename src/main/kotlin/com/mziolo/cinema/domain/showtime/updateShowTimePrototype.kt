package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.showtime.FetchShowTimes
import com.mziolo.cinema.domain.showtime.SaveShowTime
import com.mziolo.cinema.domain.showtime.ShowTime
import com.mziolo.cinema.domain.showtime.ShowTimeOverlapped

typealias UpdateShowTime = suspend (ShowTime) -> Unit

fun updateShowTimePrototype(
    fetchShowTimes: FetchShowTimes,
    saveShowTime: SaveShowTime,
): UpdateShowTime = { showTime ->

    val overLapWithOthers = fetchShowTimes(showTime.date)
        .filter { it.showTimeId != showTime.showTimeId }
        .any { it.overLap(showTime) }

    if (overLapWithOthers)
        throw ShowTimeOverlapped

    saveShowTime(showTime)
}