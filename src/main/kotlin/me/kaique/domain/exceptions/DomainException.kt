package me.kaique.domain.exceptions

import me.kaique.domain.exceptions.enums.DomainExceptionType

open class DomainException(
    val type: DomainExceptionType,
    override val message: String,
    override val cause: Throwable? = null
): Exception(message, cause)