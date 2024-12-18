package com.interview.damian_ozga.jmeter.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Configuration class for MongoDB connection and operations.
 */
public class MongoDbConfig {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE = "jmeterBenchmark";
    private static final String COLLECTION = "rawDocColl";

    /**
     * Default constructor.
     */
    public MongoDbConfig() {
    }

    /**
     * Creates and returns a new MongoClient connected to the specified URI.
     *
     * @return a MongoClient instance
     */
    public MongoClient getMongoClient(){
        return MongoClients.create(URI);
    }

    /**
     * Retrieves the specified database from the given MongoClient.
     *
     * @param mongoClient the MongoClient instance
     * @return the MongoDatabase instance for the specified database
     */
    public MongoDatabase getDatabase(MongoClient mongoClient){
        return mongoClient.getDatabase(DATABASE);
    }

    /**
     * Retrieves the specified collection from the given MongoDatabase.
     *
     * @param mongoDatabase the MongoDatabase instance
     * @return the MongoCollection instance for the specified collection
     */
    public MongoCollection<Document> getCollection(MongoDatabase mongoDatabase){
        return mongoDatabase.getCollection(COLLECTION);
    }
}