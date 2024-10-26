package dev.mbo.springkotlinerror

import dev.mbo.springkotlinerror.exctest0.ErrorCodeA
import dev.mbo.springkotlinerror.exctest0.ErrorCodeB
import dev.mbo.springkotlinerror.mapping.AbstractErrorCodeTranslator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import java.util.Locale

@Component
class TestErrorCodeTranslator(
    @Qualifier("messages") private val messageSource: MessageSource
) : AbstractErrorCodeTranslator(messageSource)

@SpringBootTest(classes = [TestApplication::class])
class TestErrorCodeTranslatorTest @Autowired constructor(
    private val translator: TestErrorCodeTranslator,
) {

    @Test
    fun testTranslatorErrorCodeAGenericEn() {
        val translation = translator.translate(
            errorCode = ErrorCodeA.GENERIC,
            args = emptyMap(),
            locale = Locale.ENGLISH,
        )
        // missing translation
        assertThat(translation.title).isEqualTo("G00001.GENERIC.TITLE")
        assertThat(translation.detail).isEqualTo("G00001.GENERIC.DETAIL")
    }

    @Test
    fun testTranslatorErrorCodeAGenericDe() {
        val translation = translator.translate(
            errorCode = ErrorCodeA.GENERIC,
            args = emptyMap(),
            locale = Locale.GERMAN,
        )
        // german translation exists
        assertThat(translation.title).isEqualTo("foo")
        assertThat(translation.detail).isEqualTo("bar")
    }

    @Test
    fun testTranslatorErrorCodeBOtherWithArgEn() {
        val translation = translator.translate(
            errorCode = ErrorCodeB.OTHER,
            args = mapOf("number" to "foo"),
            locale = Locale.ENGLISH,
        )
        // match
        assertThat(translation.title).isEqualTo("title foo.")
        assertThat(translation.detail).isEqualTo("detail foo.")
    }

    @Test
    fun testTranslatorErrorCodeBOtherWithArgDe() {
        val translation = translator.translate(
            errorCode = ErrorCodeB.OTHER,
            args = mapOf("number" to "foo"),
            locale = Locale.GERMAN,
        )
        // fallback to default language if given is missing
        assertThat(translation.title).isEqualTo("title foo.")
        assertThat(translation.detail).isEqualTo("detail foo.")
    }

}