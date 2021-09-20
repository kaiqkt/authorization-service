package me.kaique.domain.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date

class JwtUtils(private val secret: String, private val expiration: String) {

    fun generateToken(accountId: String): String {
        return Jwts.builder()
            .setSubject(accountId)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expiration.toLong()))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact()
    }

    fun validToken(token: String): Boolean {
        val claims = getClaims(token)
        if (claims != null) {
            val personId = claims.subject
            val expirationDate = claims.expiration
            val now = Date(System.currentTimeMillis())
            return personId != null && expirationDate != null && now.before(expirationDate)
        }
        return false
    }

    fun getAccountId(token: String): String? {
        val claims = getClaims(token)
        return claims?.subject
    }

    private fun getClaims(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }
    }
}