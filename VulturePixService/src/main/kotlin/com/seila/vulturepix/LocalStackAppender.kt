package com.seila.vulturepix

import org.apache.logging.log4j.core.Appender
import org.apache.logging.log4j.core.Filter
import org.apache.logging.log4j.core.Layout
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.appender.AbstractAppender
import org.apache.logging.log4j.core.config.plugins.Plugin
import org.apache.logging.log4j.core.config.plugins.PluginAttribute
import org.apache.logging.log4j.core.config.plugins.PluginFactory
import com.amazonaws.services.logs.AWSLogs
import com.amazonaws.services.logs.model.InputLogEvent
import com.amazonaws.services.logs.model.PutLogEventsRequest
import java.nio.charset.StandardCharsets
import java.util.concurrent.locks.ReentrantLock

@Plugin(name = "com.seila.vulturepix.LocalStackAppender", category = "Core", elementType = Appender.ELEMENT_TYPE, printObject = true)
class LocalStackAppender private constructor(
    name: String,
    filter: Filter?,
    layout: Layout<*>?,
    private val awsLogs: AWSLogs,
    private val logGroupName: String,
    private val logStreamName: String,
) : AbstractAppender(name, filter, layout) {


    private val lock = ReentrantLock()

    companion object {
        @JvmStatic
        @PluginFactory
        fun createAppender(
            @PluginAttribute("name") name: String?,
            @PluginAttribute("log-group")logGroupName: String,
            @PluginAttribute("log-stream") logStreamName: String,
            awsLogs: AWSLogs,
        ): LocalStackAppender? {
            if (name == null) {
                LOGGER.error("No name provided for CloudWatchAppender")
                return null
            }
//            if (logGroupName == null || logStreamName == null) {
//                LOGGER.error("Log group name or log stream name not provided for CloudWatchAppender")
//                return null
//            }
            return LocalStackAppender(name, null, null, awsLogs, logGroupName, logStreamName)
        }
    }

    override fun append(event: LogEvent) {
        println("MANDANDO PRA AWS")
//        val logMessage = String(layout.toByteArray(event), StandardCharsets.UTF_8)
        val inputLogEvent = InputLogEvent().withMessage(event.message.formattedMessage).withTimestamp(event.timeMillis)

        val putLogEventsRequest = PutLogEventsRequest()
            .withLogGroupName(logGroupName)
            .withLogStreamName(logStreamName)
            .withLogEvents(inputLogEvent)

        lock.lock()
        try {
            awsLogs.putLogEvents(putLogEventsRequest)
        } finally {
            lock.unlock()
        }
    }
}