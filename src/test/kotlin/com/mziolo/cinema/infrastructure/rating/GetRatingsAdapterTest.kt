package com.mziolo.cinema.infrastructure.rating

import com.mziolo.cinema.SpringMongoDbTest
import com.mziolo.cinema.domain.catalog.dummyMovieId
import com.mziolo.cinema.domain.rating.GetRatings
import com.mziolo.cinema.domain.rating.Rating
import com.mziolo.cinema.domain.rating.Rating.NoRatingYet
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update


internal class GetRatingsAdapterTest : SpringMongoDbTest() {

    @Autowired
    lateinit var getRatings: GetRatings

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    @Test
    internal fun shouldReturnNoRating() {
        runBlocking {
            assertEquals(NoRatingYet, getRatings(dummyMovieId))
        }
    }

    @Test
    internal fun shouldReturnProperRating() {
        runBlocking {
            updateResult(2)
            updateResult(5)

            assertEquals(Rating.RatingValue(3.5), getRatings(dummyMovieId))
        }
    }

    private suspend fun updateResult(vote: Int) = mongoOperations.upsert(
        Query(Criteria.where("movieId").`is`(dummyMovieId.id)),
        Update().inc("numberVotes", 1).inc("sumVotes", vote),
        RateDocument::class.java
    ).awaitFirst()
}