package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.catalog.Movie
import java.math.BigDecimal
import java.time.LocalTime

typealias ShowTimes = List<ShowTime>

data class ShowTime(
    val movie: Movie,
    val time: LocalTime,
    val date: ShowTimeDate,
    val price: BigDecimal
) {
    fun overLap(otherShowTime: ShowTime): Boolean {
        if(date != otherShowTime.date)
            return false
        val stopTime = stopTime()
        val otherTime = otherShowTime.time
        val otherStopTime = otherShowTime.stopTime()
        return time.isBefore(otherStopTime) && stopTime.isAfter(otherTime)
    }

    private fun stopTime() = time.plusMinutes(movie.runtimeInMinutes).plusMinutes(MINIMUM_BREAK_IN_MINUTES)
}