package me.kaique.resources.repositories.documents

import me.kaique.domain.entities.Password
import me.kaique.domain.entities.User
import java.time.LocalDateTime

data class UserDocument(
    val _id: String,
    val accountId: String,
    val email: String,
    var password: Password,
    var createdAt: LocalDateTime
) {
    fun toUser(): User = User(
        userId = this._id,
        accountId = this.accountId,
        email = this.email,
        password = this.password,
        createdAt = this.createdAt
    )

    companion object {
        fun fromUser(user: User): UserDocument = UserDocument(
            _id = user.userId,
            accountId = user.accountId,
            email = user.email,
            password = user.password,
            createdAt = user.createdAt
        )
    }
}