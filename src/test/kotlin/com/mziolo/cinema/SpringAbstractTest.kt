package com.mziolo.cinema

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import com.marcinziolo.kotlin.wiremock.contains
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.get
import com.marcinziolo.kotlin.wiremock.returnsJson
import com.mziolo.cinema.domain.catalog.ImdbId
import com.mziolo.cinema.infrastructure.sampleResponse
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest
abstract class SpringAbstractTest {
    companion object {
        val wiremockPort = 8090
        val wiremock: WireMockServer = WireMockServer(options().port(wiremockPort).notifier(ConsoleNotifier(true)))
        val url
            get() = "http://localhost:$wiremockPort"

        @BeforeAll
        @JvmStatic
        fun setUp() {
            wiremock.start()
            val imdbId = ImdbId("tt0232500")
            wiremock.get {
                url equalTo "/"
                queryParams contains "i" equalTo imdbId.id
            } returnsJson {
                body = sampleResponse
            }
        }

        @AfterAll
        @JvmStatic
        fun afterEach() {
            wiremock.resetAll()
            wiremock.stop()
        }
    }
}
