package com.mziolo.cinema.infrastructure.showtime

import com.mziolo.cinema.SpringAbstractTest
import com.mziolo.cinema.infrastructure.mongodb.ShowTimeCrudRepository
import com.mziolo.cinema.infrastructure.mongodb.ShowTimeDocument
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

internal class UpdateShowTimeAdapterTest: SpringAbstractTest() {

    @Autowired
    lateinit var showTimeCrudRepository: ShowTimeCrudRepository

    @Test
    internal fun shouldFetchDocuments() {

        val showTimeDocument = ShowTimeDocument(
            id = "aaa",
            time = LocalTime.of(20, 15),
            date = LocalDate.of(2020, 12, 14),
            price = BigDecimal("100")
        )

        runBlocking {
            showTimeCrudRepository.save(showTimeDocument).awaitFirst()
            val document = showTimeCrudRepository.findById("aaa").awaitFirst()
            println(document)
        }
    }
}