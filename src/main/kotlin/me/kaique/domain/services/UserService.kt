package me.kaique.domain.services

import me.kaique.domain.entities.User
import me.kaique.domain.exceptions.EmailAlreadyExistsException
import me.kaique.domain.gateways.persistence.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UserService(private val userRepository: UserRepository) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun create(user: User) {
        userRepository.findByEmail(user.email)
            .takeIf { it != null }
            ?.apply {
                throw EmailAlreadyExistsException(
                    "Email already registered!"
                )
            }

        userRepository.create(user)
        log.info("User with id ${user.userId} created successfully")
    }
}