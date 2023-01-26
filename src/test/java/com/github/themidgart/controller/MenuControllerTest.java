package com.github.themidgart.controller;

import com.github.themidgart.web.controller.MenuController;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.themidgart.MenuTestData.MENU_2;
import static com.github.themidgart.MenuTestData.MENU_2_ID;
import static com.github.themidgart.TestUtil.userHttpBasic;
import static com.github.themidgart.UserTestData.ADMIN;
import static com.github.themidgart.UserTestData.USER;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest extends AbstractControllerTest {
    private final String REST_URL = "/" + MenuController.REST_URL;

    @Test
    void getTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MENU_2_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(MENU_2)));
    }

    @Test
    void getNoAccess() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/" + MENU_2_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }
}