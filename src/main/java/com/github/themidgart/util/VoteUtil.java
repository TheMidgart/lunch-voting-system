package com.github.themidgart.util;

import com.github.themidgart.model.Menu;
import com.github.themidgart.model.Restaurant;
import com.github.themidgart.model.Vote;
import com.github.themidgart.to.VoteTo;
import com.github.themidgart.util.exception.IllegalVoteException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.themidgart.util.exception.ExceptionMessages.VOTE_FINISHED;

@UtilityClass
public class VoteUtil {
    public static final LocalTime ENDING_TIME = LocalTime.of(11, 0, 0);

    public static VoteTo toSummaryResults(List<Vote> results) {
        return new VoteTo(results.get(0).getMenu().getDate(), results.stream()
                .map(res -> res.getMenu().getRestaurant())
                .collect(Collectors.groupingBy(Restaurant::getName, Collectors.counting())));
    }

    public static void checkVotePossibility(Menu menu) {
        if (LocalDateTime.of(menu.getDate(), ENDING_TIME).isBefore(LocalDateTime.now())) {
            throw new IllegalVoteException(VOTE_FINISHED);
        }
    }
}
