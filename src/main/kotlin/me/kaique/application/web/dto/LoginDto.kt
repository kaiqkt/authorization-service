package me.kaique.application.web.dto

import me.kaique.domain.entities.Login

data class LoginDto(
    val email: String,
    val password: String
)

fun LoginDto.toDomain() = Login(
    email = this.email,
    password = this.password
)
