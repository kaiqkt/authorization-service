package me.kaique.application.configs.modules

import com.mongodb.ConnectionString
import me.kaique.application.configs.AuthConfig
import me.kaique.application.configs.mongoDatabase
import me.kaique.application.web.handler.ErrorHandler
import me.kaique.domain.jwt.JwtUtils
import org.koin.dsl.module.module

val dependenciesModule = module {

    single {
        AuthConfig(serviceToken = getProperty("SERVICE_TOKEN"))
    }

    single {
        ErrorHandler()
    }

    single {
        JwtUtils(
            secret = getProperty("JWT_SECRET"),
            expiration = getProperty("JWT_EXPIRATION")
        )
    }

    single {
        mongoDatabase(
            connectionString = ConnectionString("mongodb://${getProperty<String>("MONGODB_CONNECTION_STRING")}"),
            username = getProperty("MONGODB_USER"),
            password = getProperty("MONGODB_PASSWORD"),
            databaseName = getProperty("MONGODB_DATABASE")
        )
    }
}