package com.interview.damian_ozga.repository;

import com.interview.damian_ozga.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{

}