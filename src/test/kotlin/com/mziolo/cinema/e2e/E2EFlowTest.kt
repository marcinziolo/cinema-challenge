package com.mziolo.cinema.e2e

import com.mziolo.cinema.SpringAbstractTest
import com.mziolo.cinema.SpringMongoDbTest
import com.mziolo.cinema.SpringMongoDbTest.Companion.mongoDBContainer
import com.mziolo.cinema.domain.catalog.dummyMovieId
import com.mziolo.cinema.domain.showtime.anotherDummyShowTimeId
import com.mziolo.cinema.domain.showtime.dummyShowTime
import com.mziolo.cinema.domain.showtime.dummyShowTimeId
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

class E2EFlowTest: SpringMongoDbTest() {

    @Autowired
    private lateinit var webTestClient: WebTestClient
    private val movieId = dummyMovieId.id.toString()
    private val date = dummyShowTime.date.date.toString()
    private val showTimeId = dummyShowTimeId.id.toString()
    private val anotherShowTimeId = anotherDummyShowTimeId.id.toString()


    @Test
    internal fun shouldProcessSteps() {

        // add beginning there is no ratings
        getMovieAndAssertUserRating("n/a")

        voteForMovie(3)
        voteForMovie(4)

        getMovieAndAssertUserRating("3.5")

        // no show times at beginning
        fetchShowTimes(date)
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isEmpty()

        //first update creates showtime
        updateShowTime(showTimeId, "16:00")
            .expectStatus().isOk

        fetchShowTimes(date)
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.length()").isEqualTo(1)
            .jsonPath("$.[0].showTimeId").isEqualTo(showTimeId)
            .jsonPath("$.[0].time").isEqualTo("16:00")

        //second update updates updating showtime
        updateShowTime(showTimeId, "17:00")
            .expectStatus().isOk


        fetchShowTimes(date)
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.length()").isEqualTo(1)
            .jsonPath("$.[0].showTimeId").isEqualTo(showTimeId)
            .jsonPath("$.[0].time").isEqualTo("17:00")


        // another show time conflict with previous one
        updateShowTime(anotherShowTimeId, "18:00")
            .expectStatus().isEqualTo(409)


        // but at 20 00 it's ok to create another show time
        updateShowTime(anotherShowTimeId, "20:00")
            .expectStatus().isOk

        fetchShowTimes(date)
            .expectBody()
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$.[0].showTimeId").isEqualTo(showTimeId)
            .jsonPath("$.[0].time").isEqualTo("17:00")
            .jsonPath("$.[1].showTimeId").isEqualTo(anotherShowTimeId)
            .jsonPath("$.[1].time").isEqualTo("20:00")

    }

    private fun fetchShowTimes(date: String) =
        webTestClient.get().uri("/showtime/$date").exchange()

    private fun updateShowTime(showTimeId: String, time: String) =
        webTestClient.put().uri("/showtime/$showTimeId").contentType(MediaType.APPLICATION_JSON).body(
            BodyInserters.fromValue(
                """
                    {
                        "movieId": "$movieId",
                        "day": "$date",
                        "time": "$time",
                        "price": "20"
                    }
                """.trimIndent()
            )
        ).exchange()

    private fun voteForMovie(value: Int) {
        webTestClient.post().uri("/rating/$movieId").contentType(MediaType.APPLICATION_JSON).body(
            BodyInserters.fromValue(
                """
                {
                    "value": "$value"
                }
            """.trimIndent()
            )
        )
            .exchange().expectStatus().isOk
    }

    private fun getMovieAndAssertUserRating(userRating: String) {
        webTestClient.get().uri("/movie/$movieId")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("id").isEqualTo(dummyMovieId.id.toString())
            .jsonPath("userRating").isEqualTo(userRating)
    }

    companion object {
        @BeforeAll
        @JvmStatic
        fun start() {
            mongoDBContainer.start()
        }

        @AfterAll
        @JvmStatic
        fun stop() {
            mongoDBContainer.stop()
        }
    }
}