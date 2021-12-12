package com.mziolo.cinema.infrastructure.catalog.adapter

import com.mziolo.cinema.domain.catalog.dummyImdbId
import com.mziolo.cinema.infrastructure.catalog.GenerateMovieIdAdapter
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GenerateMovieIdAdapterTest {

    @Test
    internal fun shouldGenerateTheSamveMovieId() {
        val generateMovieId = GenerateMovieIdAdapter()
        assertEquals(generateMovieId(dummyImdbId), generateMovieId(dummyImdbId))
    }
}