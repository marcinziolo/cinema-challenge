package com.mziolo.cinema.infrastructure.rest


data class UpdateShowTimeDto(
    val day: String,
    val time: String,
    val price: String
)