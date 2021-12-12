package com.mziolo.cinema.infrastructure.rating

import com.mziolo.cinema.SpringMongoDbTest
import com.mziolo.cinema.domain.catalog.dummyMovieId
import com.mziolo.cinema.domain.rating.MovieVote
import com.mziolo.cinema.domain.rating.RateMovie
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations

internal class RateMovieAdapterTest : SpringMongoDbTest() {

    @Autowired
    lateinit var rateMovie: RateMovie

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    @Test
    internal fun shouldRateMovie() {
        runBlocking {
            rateMovie(MovieVote(dummyMovieId, 2))
            rateMovie(MovieVote(dummyMovieId, 5))

            val rateDocument = mongoOperations.findById(dummyMovieId.id, RateDocument::class.java).awaitFirst()

            assertEquals(RateDocument(dummyMovieId.id, numberVotes = 2, sumVotes = 7), rateDocument)
        }
    }
}