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
        val fetchMovie = mockk<FetchMovie>()
        val generateMovieId = mockk<GenerateMovieId>()

        every { fetchImdIds() } returns dummyImdbIds
        coEvery { fetchMovie(dummyImdbId, dummyMovieId) } returns dummyMovie
        every { generateMovieId(dummyImdbId) } returns dummyMovieId

        val initializeMovieCatalog = initializeMovieCatalogPrototype(
            fetchImdIds,
            fetchMovie,
            generateMovieId
        )

        //when
        assertEquals(dummyCatalog, initializeMovieCatalog())
    }
}