package me.kaique.application.web.dto

import me.kaique.domain.entities.Login

data class LoginRequest(
    val email: String,
    val password: String
)

fun LoginRequest.toDomain() = Login(
    email = this.email,
    password = this.password
)
