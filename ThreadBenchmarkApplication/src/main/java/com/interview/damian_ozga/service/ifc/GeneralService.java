package com.interview.damian_ozga.service.ifc;

/**
 * General service interface providing basic CRUD operations.
 *
 * @param <T>   the type of the document
 * @param <Key> the type of the document key
 */
public interface GeneralService<T, Key> {

    /**
     * Saves a document.
     *
     * @param documentDTO the document to save
     * @return the saved document
     */
    T save(T documentDTO);

    /**
     * Retrieves a document by its key.
     *
     * @param documentKey the key of the document
     * @return the document with the specified key
     */
    T getByKey(Key documentKey);

    /**
     * Deletes all documents.
     */
    void deleteAll();

    /**
     * Checks if a document exists by its key.
     *
     * @param documentKey the key of the document
     * @return true if the document exists, false otherwise
     */
    Boolean existByKey(Key documentKey);
}