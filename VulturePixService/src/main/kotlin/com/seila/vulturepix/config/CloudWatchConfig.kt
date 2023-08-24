package com.seila.vulturepix.config

import com.seila.vulturepix.LocalStackAppender
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.logs.AWSLogs
import com.amazonaws.services.logs.AWSLogsClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CloudWatchConfig {

    @Value("\${accesskey}")
    lateinit var accesskey: String

    @Value("\${secretKey}")
    lateinit var secretKey: String

    @Bean
    fun  credentials(): AWSCredentials {
        return BasicAWSCredentials(accesskey, secretKey)
    }

    @Value("\${cloudwatch.log-group-name}")
    lateinit var logGroupName: String

    @Value("\${cloudwatch.log-stream-name}")
    lateinit var logStreamName: String

    @Bean
    fun localStackAppender(): LocalStackAppender {
        println(logGroupName)
        println(logStreamName)
        return LocalStackAppender.createAppender(
            name = "LocalStackAppender",
            logGroupName = logGroupName,
            logStreamName = logStreamName,
            awsLogs = awsLogs())
            ?: throw IllegalStateException("Failed to create LocalStackAppender")
    }

    @Bean
    fun awsLogs(): AWSLogs {
        val logger = AWSLogsClientBuilder.standard()
        logger.setEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(
                    "http://localhost:4566",
                    Regions.US_EAST_1.name
                ))
        return logger.withCredentials(
                AWSStaticCredentialsProvider(credentials())
            ).build()
    }
}