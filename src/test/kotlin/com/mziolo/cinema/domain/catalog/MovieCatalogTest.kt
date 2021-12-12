package com.mziolo.cinema.domain.catalog

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MovieCatalogTest {

    @Test
    internal fun shouldGetMovieDetails() {
        //when
        val movieDetails = dummyCatalog.getMovie(dummyMovieId)

        //then
        assertEquals(dummyMovie, movieDetails)
    }

    @Test
    internal fun shouldValidateMovieMovieId() {
        dummyCatalog.validateMovieId(dummyMovieId)
    }

    @Test
    internal fun shouldThrowInvaliMovieId() {
        assertThrows<InvalidMovieId> {
            dummyCatalog.validateMovieId(MovieId(invnvalidMovieId))
        }
    }
}