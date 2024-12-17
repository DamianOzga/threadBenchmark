package com.interview.damian_ozga.repository;

import com.interview.damian_ozga.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository interface for managing User entities in MongoDB.
 */
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Finds a User by their email.
     *
     * @param email the email of the user
     * @return an Optional containing the found User, or empty if no User found
     */
    Optional<User> findByEmail(String email);
}