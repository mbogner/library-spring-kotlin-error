package dev.mbo.springkotlinerror

import org.springframework.boot.logging.LogLevel
import java.time.Instant

open class DomainException(
    message: String? = null,
    val type: Class<*>? = null,
    val code: ErrorCode,
    val args: Map<String, Any?>? = null,
    cause: Throwable? = null,
    val time: Instant = Instant.now(),
    val logLevel: LogLevel = LogLevel.ERROR
) : RuntimeException(message, cause)