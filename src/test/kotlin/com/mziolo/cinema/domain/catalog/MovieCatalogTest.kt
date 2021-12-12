package com.mziolo.cinema.domain.catalog

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
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
    internal fun shouldContainsDummyMovieId() {
        runBlocking {
            val delegate = mockk<suspend () -> String>()
            coEvery { delegate() } returns "dummyString"
            assertEquals("dummyString", dummyCatalog contains dummyMovieId then delegate)
        }
    }

    @Test
    internal fun shouldNotContainsInvalidMovieId() {
        assertThrows<InvalidMovieId> {
            dummyCatalog contains invalidMovieId
        }
    }
}