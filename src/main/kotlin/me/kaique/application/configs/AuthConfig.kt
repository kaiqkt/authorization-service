package me.kaique.application.configs

import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse

internal enum class Roles : Role {
    ANYONE, SERVICE, CUSTOMER
}

private const val HEADER_TOKEN_NAME = "Authorization"

class AuthConfig(private val serviceToken: String) {
    fun configure(app: Javalin) {
        app.config.accessManager { handler, ctx, permittedRoles ->
            val authorizationToken = getAuthorizationHeader(ctx)
            val role = verifyRole(authorizationToken)
            permittedRoles.takeIf { !it.contains(role) }?.apply { throw ForbiddenResponse() }
            handler.handle(ctx)
        }
    }

    private fun getAuthorizationHeader(ctx: Context): String? = ctx.header(HEADER_TOKEN_NAME)?.trim()

    private fun verifyRole(authorizationToken: String?): Role {
        if (authorizationToken == serviceToken) {
            return Roles.SERVICE
        }
        return Roles.ANYONE
    }
}