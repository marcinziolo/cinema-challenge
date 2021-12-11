package com.mziolo.cinema.infrastructure.mongodb

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration


class MongoReactiveConfiguration: AbstractReactiveMongoConfiguration() {

    @Bean
    fun mongo(): MongoClient = MongoClients.create()

    override fun getDatabaseName() = "movies"


}