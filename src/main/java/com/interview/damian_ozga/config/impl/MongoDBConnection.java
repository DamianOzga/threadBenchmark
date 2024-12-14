package com.interview.damian_ozga.config.impl;

import com.interview.damian_ozga.config.ifc.IDatabaseConnection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnection implements IDatabaseConnection<MongoClient> {
//    private static MongoDBConnection instance;
//    private MongoClient mongoClient;
//    private final String URI = "mongodb://localhost:27017";

    private MongoDBConnection() {
//        mongoClient = MongoClients.create(URI);
    }

//    public static synchronized MongoDBConnection getInstance(String uri) {
//        if (instance == null) {
//            instance = new MongoDBConnection(uri);
//        }
//        return instance;
//    }

    @Override
    public MongoClient connect() {
//        return mongoClient;
        return null;
    }

    @Override
    public void disconnect() {
//        if (mongoClient != null) {
//            mongoClient.close();
//            mongoClient = null;
//        }
    }

//    public MongoClient getMongoClient() {
//        return mongoClient;
//    }
}