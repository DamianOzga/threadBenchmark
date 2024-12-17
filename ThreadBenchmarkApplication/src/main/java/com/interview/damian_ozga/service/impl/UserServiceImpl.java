package com.interview.damian_ozga.service.impl;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.mapper.UserMapper;
import com.interview.damian_ozga.model.User;
import com.interview.damian_ozga.repository.UserRepository;
import com.interview.damian_ozga.service.ifc.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * Service implementation for managing User entities.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Saves a UserDTO entity.
     *
     * @param documentDTO the user data transfer object
     * @return the saved UserDTO
     */
    @Override
    public UserDTO save(UserDTO documentDTO) {
        User user = userMapper.toEntity(documentDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    /**
     * Retrieves a UserDTO by its key.
     *
     * @param documentKey the key of the user
     * @return the UserDTO with the specified key
     * @throws NoSuchElementException if no user is found with the specified key
     */
    @Override
    public UserDTO getByKey(String documentKey) {
        User user = userRepository.findById(documentKey)
                .orElseThrow(() -> new NoSuchElementException(
                        "User with key: " + documentKey + " doesn't exist in collection"));
        return userMapper.toDto(user);
    }

    /**
     * Deletes all users from the collection.
     */
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
        log.info("All users deleted from collection.");
    }

    /**
     * Checks if a user exists by its key.
     *
     * @param documentKey the key of the user
     * @return true if the user exists, false otherwise
     */
    @Override
    public Boolean existByKey(String documentKey) {
        return userRepository.existsById(documentKey);
    }

    /**
     * Finds a UserDTO by its email.
     *
     * @param email the email of the user
     * @return the UserDTO with the specified email
     * @throws NoSuchElementException if no user is found with the specified email
     */
    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(
                        "User with email: " + email + " doesn't exist in collection"));
        return userMapper.toDto(user);
    }
}