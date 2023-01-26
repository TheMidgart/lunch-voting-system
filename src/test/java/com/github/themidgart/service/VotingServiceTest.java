package com.github.themidgart.service;

import com.github.themidgart.ContextTestConfiguration;
import com.github.themidgart.UserTestData;
import com.github.themidgart.VotingTestData;
import com.github.themidgart.model.Vote;
import com.github.themidgart.repository.VoteRepository;
import com.github.themidgart.util.exception.IllegalVotingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.themidgart.MenuTestData.*;
import static com.github.themidgart.UserTestData.USER_ID;
import static com.github.themidgart.VotingTestData.*;

public class VotingServiceTest extends ContextTestConfiguration {
    @Autowired
    VoteService voteService;
    @Autowired
    VoteRepository voteRepository;

    @Test
    void voteChangingTest() {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTING_ID).get(), VotingTestData.VOTE);
        voteService.vote(RESTAURANT_2_ID, USER_ID, TOMORROW);
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTING_ID).get(), VotingTestData.VOTE_CHANGE);

    }

    @Test
    void voteTest() {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTING_ID).get(), VotingTestData.VOTE);
    }

    @Test
    void denyVotingTest() {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        Assertions.assertThrows(IllegalVotingException.class, () -> voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW));
    }
}