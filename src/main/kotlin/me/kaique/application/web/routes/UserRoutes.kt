package me.kaique.application.web.routes

import io.javalin.apibuilder.ApiBuilder.post
import me.kaique.application.web.controllers.UserController
import me.kaique.application.web.validator.getContentTypeWithoutCharset
import me.kaique.application.web.validator.notAcceptable

private const val REGISTER_USER_DOMAIN = "application/vnd.register_user_v1+json"

class UserRoutes(private val userController: UserController) {

    fun register() {
        post("/register") { ctx ->
            when {
                ctx.getContentTypeWithoutCharset() == REGISTER_USER_DOMAIN -> {
                    userController.register(ctx)
                }
                else -> ctx.notAcceptable()
            }
        }
    }
}