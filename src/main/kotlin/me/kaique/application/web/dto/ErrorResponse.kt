package me.kaique.application.web.dto

import me.kaique.domain.exceptions.enums.DomainExceptionType

data class ErrorResponse(
    val apiError: DomainExceptionType,
    val message: String
)
