package com.mziolo.cinema

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.options
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@Suppress("UnnecessaryAbstractClass")
@ActiveProfiles("test")
@SpringBootTest
abstract class SpringAbstractTest {
    protected val wiremockPort = 8090
    val wiremock: WireMockServer = WireMockServer(options().port(wiremockPort).notifier(ConsoleNotifier(true)))
    val url
        get() = "http://localhost:$wiremockPort"

    @BeforeEach
    fun setUp() {
        wiremock.start()
    }

    @AfterEach
    fun afterEach() {
        wiremock.resetAll()
        wiremock.stop()
    }
}
