package com.github.themidgart.controller;

import com.github.themidgart.VotingTestData;
import com.github.themidgart.repository.VoteRepository;
import com.github.themidgart.service.VoteService;
import com.github.themidgart.web.controller.VoteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.themidgart.MenuTestData.*;
import static com.github.themidgart.TestUtil.userHttpBasic;
import static com.github.themidgart.UserTestData.*;
import static com.github.themidgart.VotingTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VotingControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/" + VoteController.REST_URL;

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private VoteService voteService;

    @Test
    void getVotingOptionsByDateTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?date=" + TOMORROW)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(MENU_LIST)));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?date=" + TOMORROW))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void voteTest() throws Exception {
        perform(MockMvcRequestBuilders.post(REST_URL + "/vote/" + RESTAURANT_1_ID + "?date=" + TOMORROW)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTING_ID).get(), VotingTestData.VOTE);
    }

    @Test
    void doubleVotingTest() throws Exception {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        perform(MockMvcRequestBuilders.post(REST_URL + "/vote/" + RESTAURANT_1_ID + "?date=" + TOMORROW)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getResultsByDateTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/results?date=" + TODAY)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(RESULT)));
    }

    @Test
    void changeVoteTest() throws Exception {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        perform(MockMvcRequestBuilders.post(REST_URL + "/vote/" + RESTAURANT_2_ID + "?date=" + TOMORROW)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTING_ID).get(), VotingTestData.VOTE_CHANGE);
    }
}