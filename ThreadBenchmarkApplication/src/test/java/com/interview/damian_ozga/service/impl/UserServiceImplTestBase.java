package com.interview.damian_ozga.service.impl;

import com.interview.damian_ozga.test_base.AbstractIntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTestBase extends AbstractIntegrationTestBase {

    @Autowired
    private UserServiceImpl userService;

    @Test
    void save() {
    }

    @Test
    void getByKey() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void existByKey() {
    }

    @Test
    void findByEmail() {
    }
}