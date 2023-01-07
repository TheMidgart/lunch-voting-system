package com.github.themidgart.to;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static com.github.themidgart.util.VotingResultUtil.ENDING_TIME;


public class VotingResultTo {
    private final LocalDate date;
    private final Map<String, Long> results;

    private final boolean isFinished;

    public VotingResultTo(LocalDate date, Map<String, Long> results) {
        this.date = date;
        this.results = results;
        this.isFinished = LocalDateTime.of(date,ENDING_TIME).isBefore(LocalDateTime.now());
    }
}
