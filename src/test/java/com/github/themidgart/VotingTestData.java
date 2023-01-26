package com.github.themidgart;

import com.github.themidgart.model.Vote;
import com.github.themidgart.to.VoteTo;

import java.time.LocalDate;
import java.util.HashMap;

import static com.github.themidgart.MenuTestData.MENU_2;
import static com.github.themidgart.MenuTestData.MENU_TO_VOTE;
import static com.github.themidgart.UserTestData.USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VotingTestData {
    public static MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingAssertions(Vote.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("user.votes", "user.roles",
                            "menu.votes", "menu.dishes", "menu.restaurant", "user.password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    public static final int VOTING_ID = 100022;
    public static final HashMap<String, Long> map = new HashMap<>();
    public static final VoteTo RESULT = new VoteTo(TODAY, map);
    public static final Vote VOTE = new Vote(VOTING_ID, USER, MENU_TO_VOTE);
    public static final Vote VOTE_CHANGE = new Vote(VOTING_ID, USER, MENU_2);

    static {
        set();
    }
    public static  void set(){
        map.put("Shustriy shmel", 2L);
        VOTE.setMenu(MENU_TO_VOTE);
        VOTE_CHANGE.setMenu(MENU_2);
        VOTE.setUser(USER);
        VOTE_CHANGE.setUser(USER);
    }
}