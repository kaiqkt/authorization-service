package me.kaique.domain.exceptions

import me.kaique.domain.exceptions.enums.DomainExceptionType

//500
class CreateUserException(message: String): DomainException(
    type = DomainExceptionType.CREATE_USER_ERROR,
    message = message
)