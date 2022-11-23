package com.github.themidgart.service;

import com.github.themidgart.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void getAll() {
        List<User> users = service.getAll();
    }
}