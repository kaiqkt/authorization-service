package me.kaique.domain.cryptography

import me.kaique.domain.entities.Password
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PasswordUtilsTest {

    private val passwordUtils = PasswordUtils()

    @Test
    fun `given a password, when validate with secured password should to be return true`() {
        val originalPassword = "Pudim123#"
        val password: Password = passwordUtils.encryptPassword(originalPassword)
        val validPassword = passwordUtils.validatePassword(originalPassword, password)

        Assertions.assertTrue(validPassword)
    }

    @Test
    fun `given a password, when validate with invalid secured password should to be return false`() {
        val originalPassword = "Pudim123#"
        val password: Password = passwordUtils.encryptPassword(originalPassword)
        val validPassword = passwordUtils.validatePassword("$originalPassword@", password)

        Assertions.assertFalse(validPassword)
    }
}