package com.github.themidgart.service;

import com.github.themidgart.ContextTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.themidgart.MenuTestData.MENU_LIST;
import static com.github.themidgart.VotingTestData.TOMORROW;

public class MenuServiceTest extends ContextTestConfiguration {
    @Autowired
    MenuService menuService;
    @Test
    void getVotingOptions(){
        Assertions.assertEquals(menuService.getAllByDate(TOMORROW),MENU_LIST);
    }
}