package com.github.themidgart.service;

import com.github.themidgart.ContextTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.themidgart.MenuTestData.*;

public class MenuServiceTest extends ContextTestConfiguration {
    @Autowired
    MenuService menuService;

    @Test
    void getVoteOptions() {
        MENU_MATCHER.assertMatch(menuService.getAllByDate(TOMORROW), MENU_LIST);
    }

    @Test
    void get() {
        MENU_MATCHER.assertMatch(MENU_2, menuService.get(MENU_2_ID));
    }
}