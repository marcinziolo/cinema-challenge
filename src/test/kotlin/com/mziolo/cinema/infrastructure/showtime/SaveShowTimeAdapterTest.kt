package com.mziolo.cinema.infrastructure.showtime

import com.mziolo.cinema.SpringMongoDbTest
import com.mziolo.cinema.domain.catalog.dummyMovieId
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

internal class SaveShowTimeAdapterTest : SpringMongoDbTest() {

    @Autowired
    lateinit var repository: ShowTimeRepository

    @Autowired
    lateinit var saveShowTime: SaveShowTimeAdapter

    @Test
    internal fun shouldSaveShowTime() {
        runBlocking {

            saveShowTime(dummyShowTime)

            assertEquals(
                ShowTimeDocument(
                    id = dummyShowTimeId.id,
                    movieId = dummyMovieId.id,
                    date = dummyDate.date.toString(),
                    time = dummyTime.toString(),
                    price = dummyPrice
                ),
                repository.findById(dummyShowTimeId.id).awaitFirst()
            )
        }
    }
}