package com.mziolo.cinema.domain

import com.mziolo.cinema.domain.catalog.InvalidMovieId
import com.mziolo.cinema.domain.catalog.MovieCatalog
import com.mziolo.cinema.domain.catalog.dummyMovie
import com.mziolo.cinema.domain.catalog.dummyMovieId
import com.mziolo.cinema.domain.rating.GetRatings
import com.mziolo.cinema.domain.rating.Rating.NoRatingYet
import com.mziolo.cinema.domain.rating.dummyRating
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MovieFlowTest {
    private val movieCatalog = mockk<MovieCatalog>()
    private val getRatings = mockk<GetRatings>()

    @Test
    internal fun shouldGetMovie() = runBlocking {

        every { movieCatalog.validateMovieId(dummyMovieId) } returns Unit
        every { movieCatalog.getMovie(dummyMovieId) } returns dummyMovie
        coEvery { getRatings(dummyMovieId) } returns dummyRating

        val movieFlow = MovieFlow(movieCatalog, getRatings)

        assertEquals(MovieWithRating(dummyMovie, dummyRating), movieFlow.getMovie(dummyMovieId))
    }

    @Test
    internal fun shouldGetMovieWithoutRating() = runBlocking {

        every { movieCatalog.validateMovieId(dummyMovieId) } returns Unit
        every { movieCatalog.getMovie(dummyMovieId) } returns dummyMovie
        coEvery { getRatings(dummyMovieId) } returns NoRatingYet

        val movieFlow = MovieFlow(movieCatalog, getRatings)

        assertEquals(MovieWithRating(dummyMovie, NoRatingYet), movieFlow.getMovie(dummyMovieId))
    }

    @Test
    internal fun shouldThrowInvalidMovieId() {
        runBlocking {
            every { movieCatalog.validateMovieId(dummyMovieId) } throws InvalidMovieId
            val movieFlow = MovieFlow(movieCatalog, getRatings)

            assertThrows<InvalidMovieId> {
                movieFlow.getMovie(dummyMovieId)
            }
        }
    }
}