package dev.mbo.springkotlinerror

import jakarta.servlet.http.HttpServletRequest
import java.time.Instant
import java.util.Locale

interface ErrorMapper<DTO, ARGS> {

    fun mapToErrorDto(
        type: String? = null,
        code: ErrorCode,
        args: Map<String, Any?>? = emptyMap(),
        locale: Locale,
        request: HttpServletRequest,
        time: Instant = Instant.now()
    ): DTO

    fun mapArguments(args: Map<String, Any?>?): List<ARGS>

}