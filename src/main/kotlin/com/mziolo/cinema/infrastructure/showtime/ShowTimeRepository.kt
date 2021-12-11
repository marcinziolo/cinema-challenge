package com.mziolo.cinema.infrastructure.showtime

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface ShowTimeRepository: ReactiveCrudRepository<ShowTimeDocument, UUID>