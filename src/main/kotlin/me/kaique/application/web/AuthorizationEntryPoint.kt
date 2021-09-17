package me.kaique.application.web

import io.javalin.Javalin
import me.kaique.application.configs.AuthConfig
import me.kaique.application.configs.modules.dependenciesModule
import me.kaique.application.configs.modules.userModules
import me.kaique.application.web.handler.ErrorHandler
import me.kaique.application.web.routes.RouterManager
import org.koin.log.EmptyLogger
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.standalone.property

object AuthorizationEntryPoint : KoinComponent {

    private val authConfig: AuthConfig by inject()
    private val errorHandler: ErrorHandler by inject()
    private val serverPort: Int by property("SERVER_PORT")

    fun init(extraProperties: Map<String, Any> = emptyMap()) {

        StandAloneContext.startKoin(
            listOf(dependenciesModule, userModules),
            useEnvironmentProperties = true,
            extraProperties = extraProperties,
            logger = EmptyLogger()
        )

        Javalin.create().apply {
            authConfig.configure(this)
            errorHandler.configure(this)
            routes {
                RouterManager.registerRoutes()
            }
            start(serverPort)
        }
    }
}