package com.mziolo.cinema.infrastructure.showtime

import com.mziolo.cinema.SpringMongoDbTest
import com.mziolo.cinema.domain.catalog.dummyMovieId
import com.mziolo.cinema.domain.showtime.ShowTimeId
import com.mziolo.cinema.domain.showtime.anotherDummyShowTimeId
import com.mziolo.cinema.domain.showtime.dummyDate
import com.mziolo.cinema.domain.showtime.dummyPrice
import com.mziolo.cinema.domain.showtime.dummyShowTime
import com.mziolo.cinema.domain.showtime.dummyShowTimeId
import com.mziolo.cinema.domain.showtime.dummyTime
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

internal class FetchShowTimesAdapterTest : SpringMongoDbTest() {

    @Autowired
    lateinit var fetchShowTimesAdapter: FetchShowTimesAdapter

    @Autowired
    lateinit var repository: ShowTimeRepository

    @Test
    internal fun shouldFetchMovies() {
        runBlocking {
            //given
            //save one in one date
            repository.save(showTimeDocument(dummyShowTimeId, dummyDate.date)).awaitFirst()
            //save another with one day after
            repository.save(showTimeDocument(anotherDummyShowTimeId, dummyDate.date.plusDays(1))).awaitFirst()

            //when
            val showTimes = fetchShowTimesAdapter(dummyDate)

            //then returns only one show time for given date
            assertEquals(listOf(dummyShowTime), showTimes)
        }
    }

    private fun showTimeDocument(showTimeId: ShowTimeId, date: LocalDate) = ShowTimeDocument(
        id = showTimeId.id,
        time = dummyTime.toString(),
        date = date.toString(),
        price = dummyPrice,
        movieId = dummyMovieId.id,
        runtime = dummyShowTime.runtime
    )
}