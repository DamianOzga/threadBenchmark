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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(UserDTO documentDTO) {
        User user = userMapper.toEntity(documentDTO);
        User save = userRepository.save(user);
        log.info("User with name: {} successfully saved with key: {}.", save.getName(), save.getKey());
    }

    @Override
    public UserDTO getByKey(String documentKey) {
        User user = userRepository.findById(documentKey)
                .orElseThrow(() -> new NoSuchElementException
                        ("User with key: " + documentKey + "doesn't exist in collection"));
        return userMapper.toDto(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
        log.info("All users deleted from collection.");
    }

    @Override
    public Boolean existByKey(String documentKey) {
        return userRepository.existsById(documentKey);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException
                        ("User with email: " + email + "doesn't exist in collection"));
        return userMapper.toDto(user);
    }
}