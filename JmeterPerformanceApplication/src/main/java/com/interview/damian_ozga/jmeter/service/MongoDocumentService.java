package com.interview.damian_ozga.jmeter.service;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoDocumentService {

    private MongoCollection<Document> mongoCollection;

    public MongoDocumentService(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public void insertDoc(Document doc){
        mongoCollection.insertOne(doc);
    }

    public Document findByKey(Document doc){
        return mongoCollection.find(doc).first();
    }

    public void deleteAll(Document doc){
        mongoCollection.deleteMany(doc);
    }
}
