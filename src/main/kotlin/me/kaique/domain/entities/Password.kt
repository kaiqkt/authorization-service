package me.kaique.domain.entities

data class Password(
    val hash: String,
    val salt: String
)
