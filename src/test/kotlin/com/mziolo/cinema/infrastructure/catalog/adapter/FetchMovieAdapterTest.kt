package com.mziolo.cinema.infrastructure.catalog.adapter

import com.mziolo.cinema.SpringAbstractTest
import com.mziolo.cinema.domain.catalog.FetchMovie
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.Movie
import com.mziolo.cinema.domain.catalog.dummyMovieId
import com.mziolo.cinema.infrastructure.plot
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class FetchMovieAdapterTest : SpringAbstractTest() {
    @Autowired
    private lateinit var fetchMovie: FetchMovie

    @Test
    fun shouldFetchMovieDetails() {
        //given
        val imdbId = ImdbId("tt0232500")
        val expectedMovie = Movie(
            movieId = dummyMovieId,
            name = "The Fast and the Furious",
            releaseYear = 2001,
            description = plot,
            imdbRating = 6.8,
            runtime = 106
        )

        runBlocking {
            assertEquals(expectedMovie, fetchMovie(imdbId, dummyMovieId))
        }
    }
}
