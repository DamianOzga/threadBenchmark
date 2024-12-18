package com.interview.damian_ozga.jmeter.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDbConfig {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE = "jmeterBenchmark";
    private static final String COLLECTION = "rawDocColl";

    public MongoDbConfig() {
    }

    public MongoClient getMongoClient(){
        return MongoClients.create(URI);
    }

    public MongoDatabase getDatabase(MongoClient mongoClient){
        return mongoClient.getDatabase(DATABASE);
    }

    public MongoCollection<Document> getCollection(MongoDatabase mongoDatabase){
        return mongoDatabase.getCollection(COLLECTION);
    }
}
