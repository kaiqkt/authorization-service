package me.kaique.resources.repositories

import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import me.kaique.domain.entities.User
import me.kaique.domain.exceptions.CreateUserException
import me.kaique.domain.exceptions.EmailAlreadyExistsException
import me.kaique.domain.gateways.persistence.UserRepository
import me.kaique.resources.repositories.documents.UserDocument
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UserMongoRepository(
    mongo: MongoDatabase,
    collectionName: String
) : UserRepository {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val collection: MongoCollection<UserDocument> = mongo.getCollection(
        collectionName,
        UserDocument::class.java
    )

    override fun create(user: User) {
        val document = UserDocument.fromUser(user)

        try {
            collection.insertOne(document)
            log.info("User with id ${user.userId} persisted successfully")
        } catch (e: MongoException) {
            throw CreateUserException("Unable to insert user ${user.userId} on database")
        }
    }

    override fun findByEmail(email: String): User? {
        val document = collection.findOne(UserDocument::email eq email)

        return document?.toUser()
    }
}