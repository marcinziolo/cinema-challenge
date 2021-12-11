package com.mziolo.cinema.domain.catalog

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InitializeMovieCatalogTest {
    @Test
    internal fun shouldInitializeMovieCatalog() = runBlocking {
        val fetchImdIds = mockk<FetchImdbIds>()
        val fetchMovieDetails = mockk<FetchMovieDetails>()
        val generateMovieId = mockk<GenerateMovieId>()

        every { fetchImdIds() } returns dummyImdbIds
        coEvery { fetchMovieDetails(dummyImdbId) } returns dummyMovieDetails
        every { generateMovieId(dummyImdbId) } returns dummyMovieId

        val initializeMovieCatalog = initializeMovieCatalogPrototype(
            fetchImdIds,
            fetchMovieDetails,
            generateMovieId
        )

        //when
        assertEquals(dummyCatalog, initializeMovieCatalog())
    }
}