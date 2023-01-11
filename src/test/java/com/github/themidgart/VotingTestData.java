package com.github.themidgart;

import com.github.themidgart.model.VotingResult;
import com.github.themidgart.to.VotingResultTo;

import java.time.LocalDate;
import java.util.HashMap;

import static com.github.themidgart.MenuTestData.MENU_2;
import static com.github.themidgart.MenuTestData.MENU_TO_VOTE;

public class VotingTestData {
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    public static final int VOTING_ID = 100024;
    public static final VotingResult VOTING_RESULT = new VotingResult(VOTING_ID, UserTestData.USER, MENU_TO_VOTE);
    public static final VotingResult VOTING_CHANGING_RESULT = new VotingResult(VOTING_ID, UserTestData.USER, MENU_2);
    public static final HashMap<String, Long> map = new HashMap<>();

    static {
        map.put("Шустрый шмель", 4L);
    }

    public static final VotingResultTo RESULT = new VotingResultTo(TODAY, map);
}