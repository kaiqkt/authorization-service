package me.kaique.domain.exceptions

import me.kaique.domain.exceptions.enums.DomainExceptionType

class InvalidUserException(message: String): DomainException(
    type = DomainExceptionType.INVALID_USER,
    message = message
)