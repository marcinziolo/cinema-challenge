package com.mziolo.cinema.infrastructure.catalog

import com.fasterxml.jackson.annotation.JsonProperty

data class ImdbMovieDto(
    @JsonProperty("Title") val title: String,
    @JsonProperty("Plot") val plot: String,
    @JsonProperty("Year") val year: String,
    @JsonProperty("Ratings") val ratings: List<Rating>,
    @JsonProperty("Runtime") val runtime: String
)

data class Rating(
    @JsonProperty("Source") val source: String,
    @JsonProperty("Value") val value: String,
)