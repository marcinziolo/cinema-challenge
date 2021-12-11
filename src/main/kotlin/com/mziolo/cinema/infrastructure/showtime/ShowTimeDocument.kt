package com.mziolo.cinema.infrastructure.showtime

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Document
data class ShowTimeDocument(
    @Id val id: UUID,
    val time: LocalTime,
    val date: LocalDate,
    val movieId: UUID,
    val price: BigDecimal
)