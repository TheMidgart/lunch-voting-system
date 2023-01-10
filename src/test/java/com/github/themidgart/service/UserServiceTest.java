package com.github.themidgart.service;

import com.github.themidgart.ContextTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static com.github.themidgart.UserTestData.USER_WITH_NOT_UNIQUE_EMAIL;


class UserServiceTest extends ContextTestConfiguration {
    @Autowired
    private UserService service;

    @Test
    void denyNotUniqEmailTest() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            service.save(USER_WITH_NOT_UNIQUE_EMAIL);
        });
    }
}