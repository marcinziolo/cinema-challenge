package com.mziolo.cinema.domain.catalog

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MovieCatalogTest {

    @Test
    internal fun shouldGetMovieDetails() {
        //when
        val movieDetails = dummyCatalog.getMovie(dummyMovieId)

        //then
        assertEquals(dummyMovie, movieDetails)
    }

    @Test
    internal fun shouldContainsDummyMovieId() {
        assertTrue(dummyCatalog.contains(dummyMovieId))
    }

    @Test
    internal fun shouldNotContainsInvalidMovieId() {
        assertFalse(dummyCatalog.contains(invalidMovieId))
    }
}