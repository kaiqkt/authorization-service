package me.kaique.application.configs

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

fun mongoConnection(
    connectionString: ConnectionString,
    username: String,
    password: String,
    databaseName: String
) = KMongo.createClient(
    MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .credential(
            MongoCredential.createCredential(
                username,
                databaseName,
                password.toCharArray()
            )
        ).build()
)

fun mongoDatabase(
    connectionString: ConnectionString,
    username: String,
    password: String,
    databaseName: String
): MongoDatabase = mongoConnection(
    connectionString = connectionString,
    username = username,
    password = password,
    databaseName = databaseName
).getDatabase(databaseName)