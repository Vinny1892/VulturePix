package com.seila.vulturepix.integration.user

import com.seila.vulturepix.infrastructure.database.entity.User
import java.util.HashMap

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.json.JsonContent
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import kotlin.reflect.typeOf


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateUserIntegrationTest {

    @LocalServerPort
    private val port = 0

    @Autowired
    private val restTemplate: TestRestTemplate? = null


    @Test
    fun testCreateUserIntegration(){
        val url = "http://localhost:${port}/user"
        val jsonBody = """
            {
            	"name": "Vinicius",
            	"email": "vinnyaoe@gmail.com",
            	"password": "12345678"
            }
        """.trimIndent()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestEntity = HttpEntity(jsonBody,headers)
        val response = restTemplate?.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            String::class.java
        )
        assertNotNull(response)
        assertEquals(HttpStatus.CREATED, response?.statusCode)
    }
}