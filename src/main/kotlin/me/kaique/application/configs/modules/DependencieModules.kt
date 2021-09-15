package me.kaique.application.configs.modules

import me.kaique.application.configs.AuthConfig
import org.koin.dsl.module.module

val dependenciesModule = module {
    single {
        AuthConfig(getProperty("SERVICE_TOKEN"))
    }
}