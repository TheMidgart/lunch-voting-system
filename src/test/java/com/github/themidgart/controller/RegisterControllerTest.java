package com.github.themidgart.controller;

import com.github.themidgart.repository.UserRepository;
import com.github.themidgart.to.UserTo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.themidgart.UserTestData.ADMIN;
import static com.github.themidgart.UserTestData.NEW_USER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterControllerTest extends AbstractControllerTest {
    @Autowired
    private UserRepository repository;

    @Test
    void registerTest() throws Exception {
        perform(MockMvcRequestBuilders.post("/rest/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(NEW_USER)))
                .andExpect(status().isCreated());
        Assertions.assertNotNull(repository.findByEmailIgnoreCase(NEW_USER.getEmail()).get());
    }

    @Test
    void registerNotUniqueTest() throws Exception {
        perform(MockMvcRequestBuilders.post("/rest/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserTo(null, "test", ADMIN.getEmail(), "testtest"))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void registerNotValidTest() throws Exception {
        perform(MockMvcRequestBuilders.post("/rest/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UserTo(null, null, null, null))))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}