package dev.mbo.springkotlinerror

import jakarta.servlet.http.HttpServletRequest
import java.time.Instant
import java.util.Locale

interface ErrorMapper<T, A> {

    fun mapToErrorDto(
        type: String? = null,
        code: ErrorCode,
        args: Map<String, Any?>? = emptyMap(),
        locale: Locale,
        request: HttpServletRequest,
        time: Instant = Instant.now()
    ): T

    fun mapArguments(args: Map<String, Any?>?): List<A>

}