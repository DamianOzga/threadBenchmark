package com.interview.damian_ozga.config.impl;

import com.interview.damian_ozga.config.ifc.IDatabaseConnection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection implements IDatabaseConnection<MongoClient> {
    private static MongoDBConnection instance;
    private MongoClient mongoClient;

    private MongoDBConnection(String uri) {
        mongoClient = MongoClients.create(uri);
    }

    public static synchronized MongoDBConnection getInstance(String uri) {
        if (instance == null) {
            instance = new MongoDBConnection(uri);
        }
        return instance;
    }

    @Override
    public MongoClient connect() {
        return mongoClient;
    }

    @Override
    public void disconnect() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}