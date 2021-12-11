package com.mziolo.cinema.infrastructure.mongodb

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime


@Document
data class ShowTimeDocument(
    @Id val id: String,
    val time: LocalTime,
    val date: LocalDate,
    val price: BigDecimal
)