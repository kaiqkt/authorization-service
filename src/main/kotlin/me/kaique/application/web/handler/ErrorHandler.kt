package me.kaique.application.web.handler

import io.javalin.Javalin
import io.javalin.http.Context
import me.kaique.application.configs.Messages
import me.kaique.application.web.dto.ErrorResponse
import me.kaique.domain.exceptions.CreateUserException
import me.kaique.domain.exceptions.EmailAlreadyExistsException
import me.kaique.domain.exceptions.enums.DomainExceptionType
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ErrorHandler {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun configure(app: Javalin) {
        app.exception(CreateUserException::class.java) { e, ctx ->
            handleException(e, ctx)
        }

        app.exception(EmailAlreadyExistsException::class.java) { e, ctx ->
            handleException(e, ctx)
        }

        app.exception(Exception::class.java) { _, ctx ->
            handleException(ctx)
        }
    }

    private fun handleException(
        createUserException: CreateUserException,
        ctx: Context
    ) {
        log.error(" Create user exception error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        ctx.json(
            ErrorResponse(
                apiError = createUserException.type,
                message = Messages.CREATE_USER_ERROR
            )
        )
    }

    private fun handleException(
        emailAlreadyExistsException: EmailAlreadyExistsException,
        ctx: Context
    ) {
        log.error(" Email already used exception error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.BAD_REQUEST_400)
        ctx.json(
            ErrorResponse(
                apiError = emailAlreadyExistsException.type,
                message = Messages.CREATE_USER_ERROR
            )
        )
    }

    private fun handleException(
        ctx: Context
    ) {
        log.error(" Internal service error: ${ctx.method()} ${ctx.url()}")
        ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        ctx.json(
            ErrorResponse(
                apiError = DomainExceptionType.INTERNAL_SERVER_ERROR,
                message = Messages.INTERNAL_SERVER_ERROR)
        )
    }
}