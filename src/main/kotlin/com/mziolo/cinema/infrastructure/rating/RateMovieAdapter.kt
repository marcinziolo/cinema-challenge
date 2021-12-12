package com.mziolo.cinema.infrastructure.rating

import com.mongodb.client.model.Updates.inc
import com.mziolo.cinema.domain.rating.MovieVote
import com.mziolo.cinema.domain.rating.RateMovie
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component

@Component
class RateMovieAdapter(
    private val mongoOperations: ReactiveMongoOperations
) : RateMovie {
    override suspend fun invoke(movieVote: MovieVote) {
        mongoOperations.upsert(
            Query(Criteria.where("movieId").`is`(movieVote.movieId.id)),
            Update().inc("numberVotes", 1).inc("sumVotes", movieVote.value),
            RateDocument::class.java
        ).awaitFirst()
    }
}