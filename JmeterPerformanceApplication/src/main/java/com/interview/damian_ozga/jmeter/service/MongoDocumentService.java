package com.interview.damian_ozga.jmeter.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.model.Filters;

public class MongoDocumentService {

    private final MongoCollection<Document> mongoCollection;

    public MongoDocumentService(final MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public void insertDoc(final Document doc) {
        mongoCollection.insertOne(doc);
    }

    public Document findByKey(final Document doc) {
        return mongoCollection.find(doc).first();
    }

    public void deleteAll(final Document doc) {
        mongoCollection.deleteMany(doc);
    }

    public Document findByKey(final String key, final Object value) {
        return mongoCollection.find(Filters.eq(key, value)).first();
    }
}