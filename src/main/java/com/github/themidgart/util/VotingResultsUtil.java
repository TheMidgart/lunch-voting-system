package com.github.themidgart.util;

import com.github.themidgart.model.Menu;
import com.github.themidgart.model.Restaurant;
import com.github.themidgart.model.VotingResult;
import com.github.themidgart.to.VotingResultTo;
import com.github.themidgart.util.exception.IllegalVotingException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.themidgart.util.exception.ExceptionMessages.VOTING_FINISHED;

@UtilityClass
public class VotingResultsUtil {
    public static final LocalTime ENDING_TIME = LocalTime.of(11, 0, 0);

    public static VotingResultTo toSummaryResults(List<VotingResult> results) {
        return new VotingResultTo(results.get(0).getMenu().getDateMenu(), results.stream()
                .map(res -> res.getMenu().getRestaurant())
                .collect(Collectors.groupingBy(Restaurant::getName, Collectors.counting())));
    }

    public static void checkVotingPossibility(Menu menu) {
        if (LocalDateTime.of(menu.getDateMenu(), ENDING_TIME).isBefore(LocalDateTime.now())) {
            throw new IllegalVotingException(VOTING_FINISHED);
        }
    }
}
