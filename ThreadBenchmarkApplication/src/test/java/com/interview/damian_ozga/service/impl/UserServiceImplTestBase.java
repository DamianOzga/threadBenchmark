package com.interview.damian_ozga.service.impl;

import com.interview.damian_ozga.dto.UserDTO;
import com.interview.damian_ozga.mapper.UserMapper;
import com.interview.damian_ozga.test_base.AbstractIntegrationTestBase;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTestBase extends AbstractIntegrationTestBase {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("User saved in database")
    void user_saved_in_database() {
        // given
        val userDTO = new UserDTO();
        userDTO.setName("Test User");

        // when
        val newUserDto = userService.save(userDTO);
        val byKey = userService.getByKey(newUserDto.getKey());

        // then
        assertAll("Group of assertions for user_saved_in_database method.",
                () -> assertNotNull(newUserDto),
                () -> assertEquals(byKey.getName(), newUserDto.getName()));

    }

    @Test
    @DisplayName("Get by key returns saved user")
    void get_by_key_returns_saved_user() {
        // given
        val key = "test-key";
        val userDTO = new UserDTO();
        userDTO.setKey(key);
        userDTO.setEmail("ab@test.com");
        userService.save(userDTO);

        // when
        val userDtoByKey = userService.getByKey(key);

        // then
        assertAll("Group of assertions for get_by_key_returns_saved_user method.",
                () -> assertNotNull(userDtoByKey),
                () -> assertEquals(userDTO.getEmail(), userDtoByKey.getEmail()));
    }

    @Test
    @DisplayName("All users deleted.")
    void all_users_deleted() {
        // given
        val userDTO = new UserDTO();
        userDTO.setName("Test User");
        val userDTO2 = new UserDTO();
        userDTO.setName("Test User2");
        val newUser = userService.save(userDTO);
        val newUser2 = userService.save(userDTO2);

        // when
        userService.deleteAll();

        // then
        assertAll("Group of assertions for get_by_key_returns_saved_user method.",
                () -> assertThrows(NoSuchElementException.class, () -> userService.getByKey(newUser.getKey())),
                () -> assertThrows(NoSuchElementException.class, () -> userService.getByKey(newUser2.getKey())));
    }

    @Test
    @DisplayName("Saved user exist, random key doesn't exist")
    void saved_user_exist_random_key_does_not_exist() {
        // given
        val userDTO = new UserDTO();
        userDTO.setName("Test User");
        val newUser = userService.save(userDTO);

        // when
        val exists = userService.existByKey(newUser.getKey());
        val notExists = userService.existByKey("abc");

        // then
        assertAll("Group of assertions for saved_user_exist_random_key_does_not_exist method.",
                () -> assertTrue(exists),
                () -> assertFalse(notExists));
    }

    @Test
    @DisplayName("Get by email returns saved user")
    void get_by_email_returns_Saved_user() {
        // given
        val email = "test@example.com";
        val userDTO = new UserDTO();
        userDTO.setEmail(email);
        val newUser = userService.save(userDTO);

        // when
        val userByEmail = userService.findByEmail(newUser.getEmail());

        // then
        assertAll("Group of assertions for get_by_email_returns_Saved_user method.",
                () -> assertNotNull(userByEmail),
                () -> assertEquals(newUser.getEmail(), userByEmail.getEmail()));
    }
}