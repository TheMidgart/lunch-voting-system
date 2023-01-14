package com.github.themidgart.service;

import com.github.themidgart.ContextTestConfiguration;
import com.github.themidgart.UserTestData;
import com.github.themidgart.model.VotingResult;
import com.github.themidgart.repository.VotingResultRepository;
import com.github.themidgart.util.exception.IllegalVotingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.themidgart.MenuTestData.*;
import static com.github.themidgart.UserTestData.USER_ID;
import static com.github.themidgart.VotingTestData.VOTING_ID;
import static com.github.themidgart.VotingTestData.VOTING_RESULT_MATCHER;

public class VotingServiceTest extends ContextTestConfiguration {
    /*transfered here because when they were in VotingTestData tests fail on another platform
     * via "mvn test"*/
    public static final VotingResult VOTING_RESULT = new VotingResult(VOTING_ID, UserTestData.USER, MENU_TO_VOTE);
    public static final VotingResult VOTING_CHANGED_RESULT = new VotingResult(VOTING_ID, UserTestData.USER, MENU_2);
    @Autowired
    VotingService votingService;
    @Autowired
    VotingResultRepository votingResultRepository;

    @Test
    void voteChangingTest() {
        votingService.vote(MENU_TO_VOTE_ID, USER_ID);
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(), VOTING_RESULT);
        votingService.vote(MENU_2_ID, USER_ID);
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(), VOTING_CHANGED_RESULT);

    }

    @Test
    void voteTest() {
        votingService.vote(MENU_TO_VOTE_ID, USER_ID);
        VOTING_RESULT_MATCHER.assertMatch(votingResultRepository.findById(VOTING_ID).get(), VOTING_RESULT);
    }

    @Test
    void denyVotingTest() {
        votingService.vote(MENU_TO_VOTE_ID, USER_ID);
        Assertions.assertThrows(IllegalVotingException.class, () -> votingService.vote(MENU_TO_VOTE_ID, USER_ID));
    }
}