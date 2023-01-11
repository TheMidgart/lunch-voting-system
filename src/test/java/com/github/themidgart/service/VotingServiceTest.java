package com.github.themidgart.service;

import com.github.themidgart.ContextTestConfiguration;
import com.github.themidgart.repository.VotingResultRepository;
import com.github.themidgart.util.exception.IllegalVotingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.themidgart.MenuTestData.MENU_2_ID;
import static com.github.themidgart.MenuTestData.MENU_TO_VOTE_ID;
import static com.github.themidgart.UserTestData.USER_ID;
import static com.github.themidgart.VotingTestData.*;

public class VotingServiceTest extends ContextTestConfiguration {
    @Autowired
    VotingService votingService;
    @Autowired
    VotingResultRepository votingResultRepository;
    @Test
    void voteChangingTest(){
        votingService.vote(MENU_TO_VOTE_ID,USER_ID);
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(),VOTING_RESULT);
        votingService.vote(MENU_2_ID,USER_ID);
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(),VOTING_CHANGING_RESULT);

    }

    @Test
    void voteTest(){
        votingService.vote(MENU_TO_VOTE_ID,USER_ID);
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(),VOTING_RESULT);
    }

    @Test
    void denyVotingTest(){
        votingService.vote(MENU_TO_VOTE_ID,USER_ID);
        Assertions.assertThrows(IllegalVotingException.class,()-> votingService.vote(MENU_TO_VOTE_ID,USER_ID));
    }
}