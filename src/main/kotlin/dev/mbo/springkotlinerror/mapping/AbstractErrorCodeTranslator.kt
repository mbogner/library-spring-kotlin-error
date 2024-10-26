package dev.mbo.springkotlinerror.mapping

import dev.mbo.springkotlinerror.ErrorCode
import dev.mbo.springkotlinerror.ErrorCodeText
import org.springframework.context.MessageSource
import java.util.Locale

abstract class AbstractErrorCodeTranslator(
    private val messageSource: MessageSource
) {

    fun translate(errorCode: ErrorCode, args: Map<String, Any?>?, locale: Locale): ErrorCodeText {
        val argsArray: Array<Any?> = args?.values?.toTypedArray() ?: arrayOfNulls(0)
        return ErrorCodeText(
            title = messageSource.getMessage(errorCode.getTitleKey(), argsArray, locale),
            detail = messageSource.getMessage(errorCode.getDetailKey(), argsArray, locale)
        )
    }

}