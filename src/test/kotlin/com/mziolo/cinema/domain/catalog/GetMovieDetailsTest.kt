package com.mziolo.cinema.domain.catalog

import com.mziolo.cinema.domain.core.MovieId
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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