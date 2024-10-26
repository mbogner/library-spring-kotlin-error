package dev.mbo.springkotlinerror

import org.springframework.context.annotation.Import

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Import(ModuleErrorConfig::class)
annotation class EnableModuleError
