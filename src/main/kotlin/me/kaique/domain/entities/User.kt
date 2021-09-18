package me.kaique.domain.entities

import java.time.LocalDateTime

data class User(
    val userId: String,
    val accountId: String,
    val email: String,
    var password: Password,
    var createdAt: LocalDateTime
)