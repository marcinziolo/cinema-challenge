package com.mziolo.cinema

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [SpringMongoDbTest.Companion.MongoDbInitializer::class])
class SpringMongoDbTest {

    companion object {
        val mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:5.0"))

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