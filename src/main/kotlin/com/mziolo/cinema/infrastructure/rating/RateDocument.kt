package com.mziolo.cinema.infrastructure.rating

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document
data class RateDocument(
    @Id val movieId: UUID,
    val numberVotes: Long,
    val sumVotes: Long
)