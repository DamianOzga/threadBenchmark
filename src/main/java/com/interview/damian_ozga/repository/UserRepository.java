package com.interview.damian_ozga.repository;

import com.interview.damian_ozga.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

public class UserRepository {

    private MongoCollection<Document> collection;

    public UserRepository(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // Create a new User document
    public void createUser(Document user) {
        collection.insertOne(user);
    }

    // Read a User document by ID
    public Document readUser(String userId) {
        Bson filter = Filters.eq("id", userId);
        return collection.find(filter).first();
    }

    // Update an existing User document by ID
    public void updateUser(String userId, Document updatedUser) {
        Bson filter = Filters.eq("id", userId);
        collection.replaceOne(filter, updatedUser);
    }

    // Delete a User document by ID
    public void deleteUser(String userId) {
        Bson filter = Filters.eq("id", userId);
        collection.deleteOne(filter);
    }

}