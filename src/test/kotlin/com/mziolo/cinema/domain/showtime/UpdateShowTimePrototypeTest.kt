package com.mziolo.cinema.domain.showtime

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


internal class UpdateShowTimePrototypeTest {

    val fetchShowTimes = mockk<FetchShowTimes>()
    val saveShowTime = mockk<SaveShowTime>()

    @Test
    internal fun shouldSaveShowTimeWhenNoOverlapping() {
        runBlocking {
            //given
            val showTime = dummyShowTime
            val notOverlapShowTime = dummyShowTime.copy(
                showTimeId = anotherDummyShowTimeId,
                time = dummyTime.plusMinutes(dummyShowTime.runtime.toLong())
            )
            coEvery { fetchShowTimes(showTime.date) } returns listOf(notOverlapShowTime)
            coEvery { saveShowTime(showTime) } returns Unit

            //when
            updateShowTimePrototype(fetchShowTimes, saveShowTime)(showTime)

            //then
            coVerify(exactly = 1) { saveShowTime(showTime) }
        }
    }

    @Test
    internal fun shouldThrowExceptionsWhenOverlapped() {
        runBlocking {
            //given
            val showTime = dummyShowTime
            val overlappedShowTime = dummyShowTime.copy(
                showTimeId = anotherDummyShowTimeId,
                time = dummyTime.plusMinutes(dummyShowTime.runtime - 1L)
            )
            coEvery { fetchShowTimes(showTime.date) } returns listOf(overlappedShowTime)
            coEvery { saveShowTime(showTime) } returns Unit

            //when
            assertThrows<ShowTimeOverlapped> {
                updateShowTimePrototype(fetchShowTimes, saveShowTime)(showTime)
            }
        }
    }
}