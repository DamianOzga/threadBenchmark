package com.interview.damian_ozga.repository;

import com.interview.damian_ozga.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>{

    Optional<User> findByEmail(String email);
}