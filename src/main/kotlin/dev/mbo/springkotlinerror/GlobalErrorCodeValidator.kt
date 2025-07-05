package dev.mbo.springkotlinerror

import dev.mbo.logging.logger
import dev.mbo.springkotlinreflection.SubclassFinder
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
open class GlobalErrorCodeValidator(
    @Value("\${app.error-code.base-package:dev.mbo.ec}") private val basePackage: String
) {

    private val log = logger()
    var subclasses: Int = 0

    @PostConstruct
    fun init() {
        val idCache = mutableSetOf<String>()
        val nameCache = mutableSetOf<String>()
        val classes = SubclassFinder.findAllSubclassesOf(
            basePackage = basePackage,
            assignableType = ErrorCode::class.java
        )
        subclasses = classes.size
        classes.forEach { cls ->
            log.debug("checking {} implementation {} for duplicates", ErrorCode::class.java.simpleName, cls.name)
            if (cls.isEnum) {
                for (constant in cls.enumConstants) {
                    val name = constant.toString()
                    check(nameCache.add(name)) {
                        throw IllegalStateException("name $name has duplicates in ${cls.name} class")
                    }
                    val id = (constant as ErrorCode).getErrorId()
                    check(idCache.add(id)) {
                        throw IllegalStateException("id $id has duplicates in ${cls.name} class")
                    }
                }
            } else {
                error("Class ${cls.name} is not Enum")
            }
        }
        log.debug("validated all error ids from {}", classes)
    }

}