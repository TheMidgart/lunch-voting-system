package com.github.themidgart.util;

import com.github.themidgart.model.Menu;
import com.github.themidgart.model.Restaurant;
import com.github.themidgart.model.Vote;
import com.github.themidgart.to.VoteTo;
import com.github.themidgart.util.exception.IllegalVoteException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.github.themidgart.util.exception.ExceptionMessages.VOTE_FINISHED;

@UtilityClass
public class VoteUtil {
    public static final LocalTime ENDING_CHANGE_TIME = LocalTime.of(11, 0, 0);

    public static VoteTo toSummaryResultsByDate(List<Vote> results, LocalDate date) {
        Map<Restaurant, Long> map = results.stream()
                .filter(o -> o.getMenu().getDate().equals(date))
                .map(res -> res.getMenu().getRestaurant())
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));
        return new VoteTo(date, mapToList(map));
    }

    private static List<Object[]> mapToList(Map<Restaurant, Long> map) {
        return map.entrySet().stream()
                .map(e -> new Object[]{e.getKey().getId(), e.getKey().getName(), e.getValue()})
                .toList();
    }

    public static void checkVoteChangePossibility(Menu menu) {
        if (LocalDateTime.of(menu.getDate(), ENDING_CHANGE_TIME).isBefore(LocalDateTime.now())) {
            throw new IllegalVoteException(VOTE_FINISHED);
        }
    }
}
