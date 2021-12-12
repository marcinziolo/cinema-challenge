package com.mziolo.cinema

import com.mziolo.cinema.domain.catalog.dummyMovieId
import com.mziolo.cinema.domain.showtime.anotherDummyShowTimeId
import com.mziolo.cinema.domain.showtime.dummyShowTime
import com.mziolo.cinema.domain.showtime.dummyShowTimeId
import com.mziolo.cinema.infrastructure.rating.RateDocument
import com.mziolo.cinema.infrastructure.showtime.ShowTimeDocument
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [SpringMongoDbTest.Companion.MongoDbInitializer::class])
class SpringMongoDbTest: SpringAbstractTest() {
    @Autowired
    lateinit var operations: ReactiveMongoOperations

    @BeforeEach
    internal fun setUp() {
        runBlocking {
            operations.remove(Query(Criteria.where("movieId").`is`(dummyMovieId.id)), RateDocument::class.java).awaitFirst()
            operations.remove(Query(Criteria.where("id").`is`(dummyShowTimeId.id)), ShowTimeDocument::class.java).awaitFirst()
            operations.remove(Query(Criteria.where("id").`is`(anotherDummyShowTimeId.id)), ShowTimeDocument::class.java).awaitFirst()
        }
    }

    companion object {
        val mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:5.0"))

        @BeforeAll
        @JvmStatic
        fun start() {
            mongoDBContainer.start()
        }

        class MongoDbInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
                val values = TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDBContainer.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongoDBContainer.getMappedPort(27017)
                )
                values.applyTo(configurableApplicationContext)
            }
        }
    }
}