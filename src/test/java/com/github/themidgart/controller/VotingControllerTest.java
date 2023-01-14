package com.github.themidgart.controller;

import com.github.themidgart.repository.VotingResultRepository;
import com.github.themidgart.service.VotingService;
import com.github.themidgart.web.controller.VotingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.themidgart.MenuTestData.*;
import static com.github.themidgart.TestUtil.userHttpBasic;
import static com.github.themidgart.UserTestData.*;
import static com.github.themidgart.VotingTestData.*;
import static com.github.themidgart.service.VotingServiceTest.VOTING_CHANGED_RESULT;
import static com.github.themidgart.service.VotingServiceTest.VOTING_RESULT;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VotingControllerTest extends AbstractControllerTest {
    private static final String REST_URL = "/" + VotingController.REST_URL;

    @Autowired
    private VotingResultRepository votingResultRepository;
    @Autowired
    private VotingService votingService;

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
        perform(MockMvcRequestBuilders.get(REST_URL + "/vote/" + MENU_TO_VOTE_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(), VOTING_RESULT);
    }

    @Test
    void doubleVotingTest() throws Exception {
        votingService.vote(MENU_TO_VOTE_ID, USER_ID);
        perform(MockMvcRequestBuilders.get(REST_URL + "/vote/" + MENU_TO_VOTE_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getResultsByDateTest() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/results?date=" + TODAY)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(RESULT)));
    }

    @Test
    void changeVoteTest() throws Exception {
        votingService.vote(MENU_TO_VOTE_ID, USER_ID);
        perform(MockMvcRequestBuilders.get(REST_URL + "/vote/" + MENU_2_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(), VOTING_CHANGED_RESULT);
    }
}