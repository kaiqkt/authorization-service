package me.kaique.application.web.routes

import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.core.security.SecurityUtil
import me.kaique.application.configs.Roles
import me.kaique.application.web.Constants
import me.kaique.application.web.controllers.UserController
import me.kaique.application.web.validator.getContentTypeWithoutCharset
import me.kaique.application.web.validator.notAcceptable
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UserRoutes(private val userController: UserController) {

    fun register() {
        post("/register" ,{ ctx ->
            when {
                ctx.getContentTypeWithoutCharset() == Constants.REGISTER_USER_DOMAIN -> {
                    userController.register(ctx)
                }
                else -> ctx.notAcceptable()
            }
        }, setOf(Roles.SERVICE))

        post("/login", { ctx ->
            when {
                ctx.getContentTypeWithoutCharset() == Constants.LOGIN_DOMAIN -> {
                    userController.login(ctx)
                }
                else -> ctx.notAcceptable()
            }
        }, setOf(Roles.ANYONE))
    }
}