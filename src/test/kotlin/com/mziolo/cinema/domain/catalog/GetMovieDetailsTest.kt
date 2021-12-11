package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.UUID

private val dummyMovieId = MovieId(UUID.fromString("f3a47ecd-d73a-44a7-94ab-305d4d78e353"))
private val dummyMovieDetails = MovieDetails(
    name = "Fast&Furious",
    description = "Los Angeles police officer Brian O'Conner...",
    releaseYear = 2001,
    imdbRating = 7.6,
    runtime = "2h"
)
private val dummyCatalog = MovieCatalog(mapOf(dummyMovieId to dummyMovieDetails))

class GetMovieDetailsTest() {
    @Test
    internal fun shouldGetMovieDetails() = runBlocking {
        //given
        val initializeMovieCatalog = mockk<InitializeMovieCatalog>()
        coEvery { initializeMovieCatalog() } returns  dummyCatalog

        //when
        val getMovieDetails = getMovieDetailsPrototype(initializeMovieCatalog)

        //then
        assertEquals(dummyMovieDetails, getMovieDetails(dummyMovieId))

        //then verify that catalog was initialized only once
        getMovieDetails(dummyMovieId)
        coVerify (exactly = 1) { initializeMovieCatalog() }
    }
}