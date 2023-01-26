package com.github.themidgart.service;

import com.github.themidgart.ContextTestConfiguration;
import com.github.themidgart.repository.VoteRepository;
import com.github.themidgart.util.exception.IllegalVoteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.themidgart.MenuTestData.*;
import static com.github.themidgart.UserTestData.USER_ID;
import static com.github.themidgart.VoteTestData.*;

public class VoteServiceTest extends ContextTestConfiguration {
    @Autowired
    VoteService voteService;
    @Autowired
    VoteRepository voteRepository;

    @Test
    void voteChangingTest() {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTE_ID).get(), VOTE);
        voteService.vote(RESTAURANT_2_ID, USER_ID, TOMORROW);
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTE_ID).get(), VOTE_CHANGE);

    }

    @Test
    void voteTest() {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        VOTE_MATCHER.assertMatch(voteRepository.findById(VOTE_ID).get(), VOTE);
    }

    @Test
    void denyVoteTest() {
        voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW);
        Assertions.assertThrows(IllegalVoteException.class, () -> voteService.vote(RESTAURANT_1_ID, USER_ID, TOMORROW));
    }
}