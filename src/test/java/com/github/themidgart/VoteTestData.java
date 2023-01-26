package com.github.themidgart;

import com.github.themidgart.model.Vote;
import com.github.themidgart.to.VoteTo;

import java.util.HashMap;

import static com.github.themidgart.MenuTestData.*;
import static com.github.themidgart.UserTestData.USER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VoteTestData {
    public static MatcherFactory.Matcher<Vote> VOTE_MATCHER =
            MatcherFactory.usingAssertions(Vote.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison().ignoringFields("user.votes", "user.roles",
                            "menu.votes", "menu.dishes", "menu.restaurant", "user.password").isEqualTo(e),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
    public static final int VOTE_ID = 100022;
    public static final HashMap<String, Long> map = new HashMap<>();
    public static final VoteTo RESULT = new VoteTo(TODAY, map);
    public static final Vote VOTE = new Vote(VOTE_ID, USER, MENU_TO_VOTE);
    public static final Vote VOTE_CHANGE = new Vote(VOTE_ID, USER, MENU_2);

    static {
        map.put("Shustriy shmel", 2L);
    }
}