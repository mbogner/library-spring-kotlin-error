package dev.mbo.springkotlinerror

import dev.mbo.logging.LogLevel

interface ErrorCode {
    fun getErrorId(): String
    fun getErrorName(): String
    fun getHttpStatusCode(): Int
    fun getDefaultLogLevel(): LogLevel

    fun getTitleKey(): String {
        return "${getErrorId()}.${getErrorName()}.TITLE"
    }

    fun getDetailKey(): String {
        return "${getErrorId()}.${getErrorName()}.DETAIL"
    }
}