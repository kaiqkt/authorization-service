package me.kaique.application.web.controllers

import io.javalin.http.Context
import me.kaique.application.web.dto.LoginDto
import me.kaique.application.web.dto.TokenDto
import me.kaique.application.web.dto.UserDto
import me.kaique.application.web.dto.toDomain
import me.kaique.application.web.ext.isEmailValid
import me.kaique.application.web.ext.isPasswordValid
import me.kaique.domain.services.UserService
import org.eclipse.jetty.http.HttpStatus

class UserController(private val userService: UserService) {

    fun register(ctx: Context) {
        ctx.bodyValidator<UserDto>()
            .check({ it.email.isEmailValid() })
            .check({ it.password.isPasswordValid() })
            .get().also { dto ->
                userService.create(dto.toDomain()).apply {
                    ctx.status(HttpStatus.NO_CONTENT_204)
                }
            }
    }

    fun login(ctx: Context) {
        val dto = ctx.body<LoginDto>()
        val token = userService.authenticate(dto.toDomain())

       ctx.json(TokenDto(token)).status(HttpStatus.OK_200)
    }
}