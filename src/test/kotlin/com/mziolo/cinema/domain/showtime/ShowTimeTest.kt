package com.mziolo.cinema.domain.showtime

import com.mongodb.assertions.Assertions.assertFalse
import com.mongodb.assertions.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalTime

internal class ShowTimeTest {

    @Test
    internal fun shouldOverlap() {
        assertTrue(
            showTime(15, 0, 90).overLap(
                showTime(16, 29, 90)
            )
        )
        assertTrue(
            showTime(15, 0, 90).overLap(
                showTime(14, 16, 45)
            )
        )
    }

    @Test
    internal fun shouldNotOverlap() {
        assertFalse(
            showTime(15, 0, 90).overLap(
                showTime(16, 30, 90)
            )
        )
        assertFalse(
            showTime(15, 0, 90).overLap(
                showTime(14, 15, 45)
            )
        )
        assertFalse(
            showTime(15, 0, 90).overLap(
                showTime(14, 0, 90).copy(date = ShowTimeDate(dummyDate.date.plusDays(1)))
            )
        )
    }

    private fun showTime(hour: Int, time: Int, runtime: Int) = dummyShowTime.copy(
        time = LocalTime.of(hour, time),
        runtime = runtime
    )
}