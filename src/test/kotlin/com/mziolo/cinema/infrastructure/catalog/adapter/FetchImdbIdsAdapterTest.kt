package com.mziolo.cinema.infrastructure.catalog.adapter

import com.mziolo.cinema.AbstractTest
import com.mziolo.cinema.domain.catalog.FetchImdbIds
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.domain.catalog.ImdbIds
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

internal class FetchImdbIdsAdapterTest: AbstractTest() {

    @Autowired
    private lateinit var fetchImdbIds: FetchImdbIds

    @Test
    internal fun shouldGetMovieIds() {
        assertEquals(ImdbIds(listOf(ImdbId("tt0232500"))), fetchImdbIds())
    }
}