package com.mziolo.cinema.infrastructure.rating

import com.mziolo.cinema.domain.catalog.MovieId
import com.mziolo.cinema.domain.rating.GetRatings
import com.mziolo.cinema.domain.rating.Rating
import com.mziolo.cinema.domain.rating.Rating.NoRatingYet
import com.mziolo.cinema.domain.rating.Rating.RatingValue
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component

@Component
class GetRatingsAdapter(
    private val mongoOperations: ReactiveMongoOperations
) : GetRatings {
    override suspend fun invoke(movieId: MovieId): Rating {
        val rateDocument = mongoOperations.findById(movieId.id, RateDocument::class.java).awaitFirstOrNull()
        return if (rateDocument != null)
            RatingValue(1.0 * rateDocument.sumVotes / rateDocument.numberVotes)
        else
            NoRatingYet
    }
}