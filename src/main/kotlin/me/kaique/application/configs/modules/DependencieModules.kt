package me.kaique.application.configs.modules

import com.mongodb.ConnectionString
import me.kaique.application.configs.AuthConfig
import me.kaique.application.configs.mongoDatabase
import me.kaique.application.web.handler.ErrorHandler
import org.koin.dsl.module.module

val dependenciesModule = module {

    single {
        AuthConfig()
    }

    single {
        ErrorHandler()
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