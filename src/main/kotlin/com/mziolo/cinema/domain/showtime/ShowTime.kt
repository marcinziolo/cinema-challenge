package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.core.MovieId
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

data class ShowTime(
    val movieId: MovieId,
    val time: LocalTime,
    val date: LocalDate,
    val price: BigDecimal
)