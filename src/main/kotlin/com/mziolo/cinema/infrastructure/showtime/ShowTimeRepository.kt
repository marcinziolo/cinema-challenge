package com.mziolo.cinema.infrastructure.showtime

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.UUID


@Repository
interface ShowTimeRepository : ReactiveCrudRepository<ShowTimeDocument, UUID> {
    fun findAllByDate(date: String): Flux<ShowTimeDocument>
}