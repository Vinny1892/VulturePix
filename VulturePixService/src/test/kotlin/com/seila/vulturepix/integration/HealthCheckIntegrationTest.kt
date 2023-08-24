package com.seila.vulturepix.integration

import java.util.HashMap

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthCheckIntegrationTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null


    @Test
    fun testHealthCheckIntegration(){
        val url = "http://localhost:${port}/health-check"
        val response = restTemplate?.getForObject(url, HashMap::class.java)
        assertNotNull(response)
        assertEquals(response?.get("message"), "está funcinando essa porra")
    }
}