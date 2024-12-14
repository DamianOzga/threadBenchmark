package com.interview.damian_ozga.service.impl;

import com.interview.damian_ozga.model.User;
import com.interview.damian_ozga.repository.UserRepository;
import com.interview.damian_ozga.service.ifc.IUserService;

public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public void createUser(User user) {
//        userRepository.createUser(user.toDocument());
//    }
//
//    @Override
//    public User readUser(String userId) {
//        return User.fromDocument(userRepository.readUser(userId));
//    }
//
//    @Override
//    public void updateUser(String userId, User user) {
//        userRepository.updateUser(userId, user.toDocument());
//    }
//
//    @Override
//    public void deleteUser(String userId) {
//        userRepository.deleteUser(userId);
//    }
}