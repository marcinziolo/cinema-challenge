package com.mziolo.cinema.domain.showtime

import com.mziolo.cinema.domain.catalog.dummyMovie
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

val dummyTime = LocalTime.of(18, 0)
val dummyDate = ShowTimeDate(LocalDate.of(2021, 12, 12))
val dummyPrice = BigDecimal("20")
val dummyShowTime = ShowTime(
    movie = dummyMovie,
    time = dummyTime,
    date = dummyDate,
    price = dummyPrice
)