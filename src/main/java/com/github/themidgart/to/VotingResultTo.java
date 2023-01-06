package com.github.themidgart.to;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;


public class VotingResultTo {
    public static final LocalTime ENDING_TIME = LocalTime.of(11,0,0);
    private final LocalDate date;
    private final Map<String, Long> results;

    private final boolean isFinished;

    public VotingResultTo(LocalDate date, Map<String, Long> results) {
        this.date = date;
        this.results = results;
        this.isFinished = LocalDateTime.of(date,ENDING_TIME).isBefore(LocalDateTime.now());
    }
}
