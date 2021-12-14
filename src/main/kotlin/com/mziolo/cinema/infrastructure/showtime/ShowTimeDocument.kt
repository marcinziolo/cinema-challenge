package com.mziolo.cinema.infrastructure.showtime

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.UUID

@Document
data class ShowTimeDocument(
    @Id val id: UUID,
    val time: String,
    val date: String,
    val movieId: UUID,
    val price: BigDecimal,
    val runtime: Int
)