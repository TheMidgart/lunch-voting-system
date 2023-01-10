package com.github.themidgart;

import com.github.themidgart.model.VotingResult;

import java.time.LocalDate;

import static com.github.themidgart.MenuTestData.MENU_2;
import static com.github.themidgart.MenuTestData.MENU_TO_VOTE;
import static org.assertj.core.api.Assertions.assertThat;

public class VotingTestData {
    public static MatcherFactory.Matcher<VotingResult> VOTING_RESULT_MATCHER =
            MatcherFactory.usingAssertions(VotingResult.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("user.votingResults", "user.roles",
                            "menu.votingResults", "menu.dishes", "menu.restaurant").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);

    public static final int VOTING_ID = 100024;


    public static final VotingResult VOTING_RESULT = new VotingResult(VOTING_ID, UserTestData.USER, MENU_TO_VOTE);
    public static final VotingResult VOTING_CHANGING_RESULT = new VotingResult(VOTING_ID, UserTestData.USER, MENU_2);
}
