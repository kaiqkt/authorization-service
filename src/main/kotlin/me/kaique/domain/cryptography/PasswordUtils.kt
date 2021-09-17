package me.kaique.domain.cryptography

import me.kaique.domain.entities.Password
import java.lang.Integer.parseInt
import java.math.BigInteger
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import kotlin.experimental.or
import kotlin.experimental.xor

private const val ITERATIONS = 10000
private const val KEY_LENGTH = 256
private const val SALT_SIZE = 16
private const val HEX_BITS = 16

class PasswordUtils {

    fun encryptPassword(password: String): Password {
        val arrayPassword = password.toCharArray()
        val salt = generateSalt()

        val hash = encodedPassword(arrayPassword, salt, ITERATIONS)

        return Password(hash = toHex(hash), salt = toHex(salt))
    }

    fun validatePassword(password: String, securedPassword: Password): Boolean {
        val hash = fromHex(securedPassword.hash)
        val hashOriginalPassword = encodedPassword(
            password.toCharArray(),
            fromHex(securedPassword.salt),
            ITERATIONS
        )

        var diff = hash.size.toByte() xor hashOriginalPassword.size.toByte()

        hash.forEachIndexed { index, byte ->
            kotlin.run {
                if (index > hashOriginalPassword.size) return false

                val diffTemp = (byte xor hashOriginalPassword[index])
                diff = diff or diffTemp
            }
        }

        return diff.toInt() == 0
    }

    private fun encodedPassword(arrayPassword: CharArray, salt: ByteArray, iterations: Int): ByteArray {
        val keySpec = PBEKeySpec(arrayPassword, salt, iterations, KEY_LENGTH)
        val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")

        return skf.generateSecret(keySpec).encoded
    }

    private fun generateSalt(): ByteArray {
        val secureRandom = SecureRandom.getInstance("SHA1PRNG")
        val salt = ByteArray(SALT_SIZE)
        secureRandom.nextBytes(salt)

        return salt
    }

    private fun toHex(array: ByteArray): String {
        val bigInteger = BigInteger(1, array)
        val stringHex = bigInteger.toString(HEX_BITS)
        val paddingLength = (array.size * 2) - stringHex.length

        return if (paddingLength > 0) {
            String.format("%0" + paddingLength + "d", 0) + stringHex
        } else {
            stringHex
        }
    }

    private fun fromHex(hex: String): ByteArray {
        val bytes = ByteArray(hex.length / 2)
        for (i in bytes.indices) {
            bytes[i] = (parseInt(hex.substring(2 * i, 2 * i + 2), HEX_BITS)).toByte()
        }

        return bytes
    }
}