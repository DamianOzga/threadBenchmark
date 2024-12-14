package com.interview.damian_ozga.service.impl;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.mapper.UserMapper;
import com.interview.damian_ozga.model.User;
import com.interview.damian_ozga.repository.UserRepository;
import com.interview.damian_ozga.service.ifc.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void save(UserDTO entity) {
        User user = userMapper.toEntity(entity);
        User save = userRepository.save(user);
        log.info("User with name: {} successfully saved with key: {}.", save.getName(), save.getKey());
    }

    @Override
    public UserDTO getById(String entityId) {
        User user = userRepository.findById(entityId)
                .orElseThrow(() -> new NoSuchElementException
                        ("User with key: " + entityId + "doesn't exist in collection"));
        return userMapper.toDto(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
        log.info("All users deleted from collection.");
    }
}