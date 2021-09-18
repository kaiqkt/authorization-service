package me.kaique.domain.services

import me.kaique.domain.cripto.PasswordUtils
import me.kaique.domain.entities.Login
import me.kaique.domain.entities.User
import me.kaique.domain.exceptions.EmailAlreadyExistsException
import me.kaique.domain.exceptions.InvalidUserException
import me.kaique.domain.gateways.persistence.UserRepository
import me.kaique.domain.jwt.JwtUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UserService(private val userRepository: UserRepository, private val jwtUtils: JwtUtils) {

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

    fun authenticate(login: Login): String {
        val cipher = PasswordUtils()

        userRepository.findByEmail(login.email)?.let {
            if (cipher.validatePassword(login.password, it.password)) {
                return jwtUtils.generateToken(it.accountId)
            }
            throw InvalidUserException("Invalid password")
        }
        throw InvalidUserException("Invalid email")
    }
}