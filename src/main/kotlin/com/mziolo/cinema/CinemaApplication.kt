package com.mziolo.cinema

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
class CinemaApplication

fun main(args: Array<String>) {
	runApplication<CinemaApplication>(*args)
}
