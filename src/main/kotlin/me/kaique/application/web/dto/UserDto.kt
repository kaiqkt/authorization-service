package me.kaique.application.web.dto

import io.azam.ulidj.ULID
import me.kaique.domain.cripto.PasswordUtils
import me.kaique.domain.entities.User
import java.time.LocalDateTime

data class UserDto(
    val accountId: String,
    val email: String,
    val password: String
)

fun UserDto.toDomain() = User(
    userId = ULID.random(),
    accountId = this.accountId,
    email = this.email,
    password = PasswordUtils().encryptPassword(this.password),
    createdAt = LocalDateTime.now()
)