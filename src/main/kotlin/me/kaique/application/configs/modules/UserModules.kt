package me.kaique.application.configs.modules

import me.kaique.application.web.controllers.UserController
import me.kaique.application.web.routes.UserRoutes
import me.kaique.domain.gateways.persistence.UserRepository
import me.kaique.domain.services.UserService
import me.kaique.resources.repositories.UserMongoRepository
import org.koin.dsl.module.module

val userModules = module {

    single<UserRepository> {
        UserMongoRepository(
            mongo = get(),
            collectionName = getProperty("MONGODB_AUTHORIZATION_COLLECTION")
        )
    }

    single { UserService(userRepository = get())
    }

    single { UserController(userService = get())
    }

    single { UserRoutes(userController = get()) }
}