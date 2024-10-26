package dev.mbo.springkotlinerror

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackageClasses = [ModuleErrorConfig::class])
internal open class ModuleErrorConfig