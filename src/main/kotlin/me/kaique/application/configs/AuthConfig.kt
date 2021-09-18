package me.kaique.application.configs

import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.UnauthorizedResponse

internal enum class Roles : Role {
    CUSTOMER, SERVICE
}

private const val HEADER_TOKEN_NAME = "Authorization"

class AuthConfig(private val serviceToken: String) {
    fun configure(app: Javalin) {
        app.config.accessManager { handler, ctx, _ ->
            val authorizationToken = getAuthorizationHeader(ctx)
            verifyRole(authorizationToken)
            handler.handle(ctx)
        }
    }

    private fun getAuthorizationHeader(ctx: Context): String? = ctx.header(HEADER_TOKEN_NAME)?.trim()

    private fun verifyRole(authorizationToken: String?): Role? {
        if (authorizationToken == serviceToken) {
            return Roles.SERVICE
        }
        throw UnauthorizedResponse()
    }
}