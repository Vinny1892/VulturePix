package com.seila.vulturepix.infrastructure.web.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.log

@RestController
@RequestMapping("/health-check")
class HealthCheckController {

    var logger = LoggerFactory.getLogger(HealthCheckController::class.java)

    @GetMapping()
    fun handle(): Map<String,String> {
        logger.info("CAIU AQUI NESSA REQUUEST")
        return mapOf("message" to "está funcinando essa porra")
    }
}