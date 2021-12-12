package com.mziolo.cinema.infrastructure.rest

import java.util.UUID


data class UpdateShowTimeDto(
    val movieId: UUID,
    val day: String,
    val time: String,
    val price: String
)