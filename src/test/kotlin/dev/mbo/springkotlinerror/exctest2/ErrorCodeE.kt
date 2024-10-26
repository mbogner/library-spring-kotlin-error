package dev.mbo.springkotlinerror.exctest2

import dev.mbo.logging.LogLevel
import dev.mbo.springkotlinerror.ErrorCode

enum class ErrorCodeE(
    private val id: String,
    private val httpStatusCode: Int,
    private val defaultLogLevel: LogLevel,
) : ErrorCode {

    GENERIC("FOO", 200, LogLevel.ERROR),
    ;

    override fun getErrorId(): String {
        return id
    }

    override fun getErrorName(): String {
        return this.name
    }

    override fun getDefaultLogLevel(): LogLevel {
        return this.defaultLogLevel
    }

    override fun getHttpStatusCode(): Int {
        return httpStatusCode
    }

}