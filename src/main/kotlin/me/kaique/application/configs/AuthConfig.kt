package me.kaique.application.configs

import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Context

internal enum class Roles : Role {
    USER, ADM
}

private const val HEADER_TOKEN_NAME = "Authorization"

class AuthConfig() {
    fun configure(app: Javalin) {
        app.config.accessManager { handler, ctx, _ ->
            val authorizationToken = getAuthorizationHeader(ctx)
//            verifyRole(authorizationToken)
            handler.handle(ctx)
        }
    }

    private fun getAuthorizationHeader(ctx: Context): String? = ctx.header(HEADER_TOKEN_NAME)?.trim()

//    private fun verifyRole(authorizationToken: String?): Role? {
//        if (authorizationToken == serviceToken) {
//            return Roles.CUSTOMER
//        }
//        throw UnauthorizedResponse()
//    }
}