package dev.mbo.springkotlinerror

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.nio.charset.StandardCharsets
import java.util.Locale

@EnableModuleError
@SpringBootTest
class TestApplication {

    @Suppress("unused") // we don't want to use the bean init for tests
    @MockitoBean
    private lateinit var errorCodeValidator: GlobalErrorCodeValidator

    @Bean
    fun localeResolver(): LocaleResolver {
        return AcceptHeaderLocaleResolver().apply {
            setDefaultLocale(Locale.ENGLISH)
            supportedLocales = listOf(Locale.ENGLISH, Locale.GERMAN)
        }
    }

    @Bean
    @Qualifier("messages")
    fun messageSource(): MessageSource {
        return ReloadableResourceBundleMessageSource().apply {
            setBasename("classpath:locale/messages")
            setDefaultEncoding(StandardCharsets.UTF_8.displayName())
            setUseCodeAsDefaultMessage(true)
        }
    }

}

fun main(args: Array<String>) {
    fromApplication<TestApplication>().run(*args)
}
