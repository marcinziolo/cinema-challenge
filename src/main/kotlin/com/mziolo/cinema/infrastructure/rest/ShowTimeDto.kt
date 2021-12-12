package com.mziolo.cinema.infrastructure.rest

import java.util.UUID


data class ShowTimeDto(
    val movieId: UUID,
    val showTimeId: UUID,
    val day: String,
    val time: String,
    val price: String,
    val runtime: Int
)