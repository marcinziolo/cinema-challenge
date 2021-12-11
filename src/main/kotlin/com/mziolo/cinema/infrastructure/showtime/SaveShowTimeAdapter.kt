package com.mziolo.cinema.infrastructure.showtime

import com.mziolo.cinema.domain.showtime.SaveShowTime
import com.mziolo.cinema.domain.showtime.ShowTime
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component

@Component
class SaveShowTimeAdapter(
    val repository: ShowTimeRepository
): SaveShowTime {
    override suspend fun invoke(showTime: ShowTime) {
        repository.save(showTime.toDocument()).awaitFirst()
    }
}

private fun ShowTime.toDocument(): ShowTimeDocument = ShowTimeDocument(
    id = this.showTimeId.id,
    movieId = this.movieId.id,
    date = this.date.date.toString(),
    time = this.time.toString(),
    price = this.price
)