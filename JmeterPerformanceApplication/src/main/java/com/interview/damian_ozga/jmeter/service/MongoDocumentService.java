package com.interview.damian_ozga.jmeter.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.model.Filters;

/**
 * Service class for performing CRUD operations on MongoDB documents.
 */
public class MongoDocumentService {

    private final MongoCollection<Document> mongoCollection;

    /**
     * Constructs a new MongoDocumentService with the given MongoDB collection.
     *
     * @param mongoCollection the MongoDB collection to perform operations on
     */
    public MongoDocumentService(final MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    /**
     * Inserts a document into the MongoDB collection.
     *
     * @param doc the document to insert
     */
    public void insertDoc(final Document doc) {
        mongoCollection.insertOne(doc);
    }

    /**
     * Finds a document in the MongoDB collection by matching the provided document.
     *
     * @param doc the document to match
     * @return the first matching document, or null if no match is found
     */
    public Document findByKey(final Document doc) {
        return mongoCollection.find(doc).first();
    }

    /**
     * Deletes all documents in the MongoDB collection that match the provided document.
     *
     * @param doc the document to match for deletion
     */
    public void deleteAll(final Document doc) {
        mongoCollection.deleteMany(doc);
    }

    /**
     * Finds a document in the MongoDB collection by a specific key-value pair.
     *
     * @param key the key to match
     * @param value the value to match
     * @return the first matching document, or null if no match is found
     */
    public Document findByKey(final String key, final Object value) {
        return mongoCollection.find(Filters.eq(key, value)).first();
    }
}