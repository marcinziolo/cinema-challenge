package com.mziolo.cinema.infrastructure.mongodb

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface ShowTimeCrudRepository: ReactiveCrudRepository<ShowTimeDocument, String>