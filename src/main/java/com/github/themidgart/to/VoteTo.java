package com.github.themidgart.to;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static com.github.themidgart.util.VoteUtil.ENDING_TIME;


public class VoteTo {
    private LocalDate menuDate;
    private Map<String, Long> results;

    private boolean isFinished;

    public VoteTo(LocalDate menuDate, Map<String, Long> results) {
        this.menuDate = menuDate;
        this.results = results;
        this.isFinished = LocalDateTime.of(menuDate, ENDING_TIME).isBefore(LocalDateTime.now());
    }
}
