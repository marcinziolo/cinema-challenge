package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.catalog.Movie
import com.mziolo.cinema.domain.catalog.MovieId
import java.math.BigDecimal
import java.time.LocalTime

typealias ShowTimes = List<ShowTime>

const val MINIMUM_BREAK_IN_MINUTES = 15

data class ShowTime(
    val movieId: MovieId,
    val showTimeId: ShowTimeId,
    val time: LocalTime,
    val date: ShowTimeDate,
    val price: BigDecimal,
    val runtime: Int
) {
    fun overLap(otherShowTime: ShowTime): Boolean {
        if (date != otherShowTime.date)
            return false
        val otherTime = otherShowTime.time
        val otherStopTime = otherShowTime.stopTime()
        return time.isBefore(otherStopTime) && stopTime().isAfter(otherTime)
    }

    private fun stopTime() = time.plusMinutes(runtime.toLong())

    infix fun runtimeIsEnoughLongerThan(movie: Movie): ValidRuntime {
        if(runtime < MINIMUM_BREAK_IN_MINUTES + movie.runtime)
            throw TooShortRuntime
        return ValidRuntime
    }

    object ValidRuntime {
        suspend infix fun <R> then(delegate: suspend () -> R)  = delegate()
    }
}