package com.mziolo.cinema.domain.showtime

import com.mongodb.assertions.Assertions.assertFalse
import com.mongodb.assertions.Assertions.assertTrue
import com.mziolo.cinema.domain.catalog.dummyMovie
import org.junit.jupiter.api.Test
import java.time.LocalTime

internal class ShowTimeTest {

    @Test
    internal fun shouldOverlap() {
        assertTrue(
            showTime(15, 0, 90).overLap(
                showTime(16, 44, 90)
            )
        )
        assertTrue(
            showTime(15, 0, 90).overLap(
                showTime(14, 16, 30)
            )
        )
    }

    @Test
    internal fun shouldNotOverlap() {
        assertFalse(
            showTime(15, 0, 90).overLap(
                showTime(16, 45, 90)
            )
        )
        assertFalse(
            showTime(15, 0, 90).overLap(
                showTime(14, 15, 30)
            )
        )
        assertFalse(
            showTime(15, 0, 90).overLap(
                showTime(14, 16, 30).copy(date = ShowTimeDate(dummyDate.date.plusDays(1)))
            )
        )
    }

    private fun showTime(hour: Int, time: Int, runtime: Int) = dummyShowTime.copy(
        time = LocalTime.of(hour, time),
        movie = dummyMovie.copy(runtimeInMinutes = runtime.toLong())
    )
}