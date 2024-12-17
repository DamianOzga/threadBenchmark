package com.interview.damian_ozga.config.mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * MongoDB's configuration class for creating and configuring MongoDB client and template beans.
 */
@Configuration
public class MongoConfig {

    // Connection string to connect to MongoDB
    private final String connectionString;

    // Database name to be used with MongoDB
    private final String databaseName;

    /**
     * Constructor to initialize MongoConfig with MongoDB connection string and database name.
     *
     * @param connectionString the connection string for MongoDB
     * @param databaseName the name of the MongoDB database
     */
    public MongoConfig(
            @Value("${spring.data.mongodb.uri}") String connectionString,
            @Value("${spring.data.mongodb.database}") String databaseName) {
        this.connectionString = connectionString;
        this.databaseName = databaseName;
    }

    /**
     * Creates a MongoClient bean to interact with MongoDB.
     *
     * @return a MongoClient instance
     */
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(connectionString);
    }

    /**
     * Creates a MongoTemplate bean to perform operations on MongoDB.
     *
     * @return a MongoTemplate instance
     */
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), databaseName);
    }
}