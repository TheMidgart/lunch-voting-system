package com.github.themidgart.to;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static com.github.themidgart.util.VoteUtil.ENDING_TIME;

@Data
public class VoteTo {
    private LocalDate menuDate;
    private Map<String, Long> results;

    private boolean isVoiceChangeEnabled;

    public VoteTo(LocalDate menuDate, Map<String, Long> results) {
        this.menuDate = menuDate;
        this.results = results;
        this.isVoiceChangeEnabled = LocalDateTime.of(menuDate, ENDING_TIME).isBefore(LocalDateTime.now());
    }
}
