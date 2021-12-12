package com.mziolo.cinema.infrastructure.showtime

import com.mziolo.cinema.domain.catalog.MovieId
import com.mziolo.cinema.domain.showtime.FetchShowTimes
import com.mziolo.cinema.domain.showtime.ShowTime
import com.mziolo.cinema.domain.showtime.ShowTimeDate
import com.mziolo.cinema.domain.showtime.ShowTimeId
import com.mziolo.cinema.domain.showtime.ShowTimes
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalTime

@Component
class FetchShowTimesAdapter(
    private val repository: ShowTimeRepository
) : FetchShowTimes {
    override suspend fun invoke(showTimeDate: ShowTimeDate): ShowTimes = repository
        .findAllByDate(showTimeDate.date.toString())
        .asFlow()
        .toList()
        .map(ShowTimeDocument::toModel)
}

private fun ShowTimeDocument.toModel(): ShowTime = ShowTime(
    showTimeId = ShowTimeId(this.id),
    movieId = MovieId(this.movieId),
    date = ShowTimeDate(LocalDate.parse(this.date)),
    time = LocalTime.parse(this.time),
    price = this.price,
    runtime = null
)