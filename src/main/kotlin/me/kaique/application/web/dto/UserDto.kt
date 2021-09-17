package me.kaique.application.web.dto

import io.azam.ulidj.ULID
import me.kaique.domain.cryptography.PasswordUtils
import me.kaique.domain.entities.User
import java.time.LocalDateTime

data class UserDto(
    val email: String,
    val password: String
)

fun UserDto.toDomain() = User(
    userId = ULID.random(),
    email = this.email,
    password = PasswordUtils().encryptPassword(this.password),
    createdAt = LocalDateTime.now()
)