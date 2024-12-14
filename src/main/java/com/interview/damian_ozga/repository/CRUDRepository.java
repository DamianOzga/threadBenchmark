package com.interview.damian_ozga.repository;

import org.bson.Document;

public interface CRUDRepository {

    void createDocument(Document document);

    Document readDocument(String documentId);

    void updateDocument(String documentId, Document updatedDocument);

    void deleteDocument(String documentId);
}
