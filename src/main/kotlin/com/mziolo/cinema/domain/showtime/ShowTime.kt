package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.catalog.MovieId
import java.math.BigDecimal
import java.time.LocalTime

typealias ShowTimes = List<ShowTime>

data class ShowTime(
    val movieId: MovieId,
    val showTimeId: ShowTimeId,
    val time: LocalTime,
    val date: ShowTimeDate,
    val price: BigDecimal,
    val runtime: Int?
) {
    fun overLap(otherShowTime: ShowTime): Boolean {
        if(date != otherShowTime.date)
            return false
        val stopTime = stopTime()
        val otherTime = otherShowTime.time
        val otherStopTime = otherShowTime.stopTime()
        return time.isBefore(otherStopTime) && stopTime.isAfter(otherTime)
    }

    private fun stopTime() = time.plusMinutes(runtime!!.toLong())
}