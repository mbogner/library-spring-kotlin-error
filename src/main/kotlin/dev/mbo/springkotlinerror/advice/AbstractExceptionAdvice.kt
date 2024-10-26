package dev.mbo.springkotlinerror.advice

import dev.mbo.springkotlinerror.ErrorCode
import dev.mbo.springkotlinerror.ErrorMapper
import dev.mbo.logging.LeveledLogger
import dev.mbo.logging.logger
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import java.time.Instant
import java.util.Locale

abstract class AbstractExceptionAdvice<T, A>(
    private val errorMapper: ErrorMapper<T, A>,
) {

    private val log = logger()

    protected fun handle(
        exc: Exception,
        code: ErrorCode,
        locale: Locale,
        request: HttpServletRequest,
        args: Map<String, Any?>?,
        time: Instant = Instant.now(),
    ): ResponseEntity<T> {
        LeveledLogger.log(log, code.getDefaultLogLevel(), exc)
        val dto = errorMapper.mapToErrorDto(
            type = exc.javaClass.name,
            code = code,
            args = args,
            locale = locale,
            request = request,
            time = time,
        )
        return ResponseEntity.status(code.getHttpStatusCode()).body(dto)
    }

}