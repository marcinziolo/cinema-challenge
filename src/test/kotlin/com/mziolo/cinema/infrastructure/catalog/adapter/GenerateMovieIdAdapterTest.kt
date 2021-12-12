package com.mziolo.cinema.infrastructure.catalog.adapter

import com.mziolo.cinema.domain.catalog.dummyImdbId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GenerateMovieIdAdapterTest {

    @Test
    internal fun shouldGenerateTheSamveMovieId() {
        val generateMovieId = GenerateMovieIdAdapter()
        assertEquals(generateMovieId(dummyImdbId), generateMovieId(dummyImdbId))
    }
}