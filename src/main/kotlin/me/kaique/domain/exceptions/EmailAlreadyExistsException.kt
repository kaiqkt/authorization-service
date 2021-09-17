package me.kaique.domain.exceptions

import me.kaique.domain.exceptions.enums.DomainExceptionType

//400
class EmailAlreadyExistsException(message: String): DomainException(
    type = DomainExceptionType.EMAIL_ALREADY_USED,
    message = message
)