package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.catalog.dummyMovie
import com.mziolo.cinema.domain.catalog.dummyMovieId
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

val dummyShowTimeId = ShowTimeId(UUID.fromString("5f9a432e-1723-44dd-94fb-5d6bfe9dfe3a"))
val anotherDummyShowTimeId = ShowTimeId(UUID.fromString("12392b03-b687-44d7-a842-01761ff9d426"))
val dummyTime = LocalTime.of(18, 0)
val dummyDate = ShowTimeDate(LocalDate.of(2021, 12, 12))
val dummyPrice = BigDecimal("20")
val dummyShowTime = ShowTime(
    showTimeId = dummyShowTimeId,
    movieId = dummyMovieId,
    time = dummyTime,
    date = dummyDate,
    price = dummyPrice,
    runtime = dummyMovie.runtimeInMinutes
)