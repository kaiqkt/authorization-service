package me.kaique.domain.gateways.persistence

import me.kaique.domain.entities.User

interface UserRepository {
    fun create(user: User)
    fun findByEmail(email: String): User?
}