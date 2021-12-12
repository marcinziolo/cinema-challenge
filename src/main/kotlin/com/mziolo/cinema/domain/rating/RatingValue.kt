package com.mziolo.cinema.domain.rating

sealed class Rating {
    data class RatingValue(
        val value: Double
    ): Rating()

    object NoRatingYet: Rating()
}

