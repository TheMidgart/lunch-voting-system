package com.github.themidgart.to;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.themidgart.util.VoteUtil.ENDING_CHANGE_TIME;

@Data
public class VoteTo {
    private LocalDate menuDate;
    
    /* 0 index - id
     * 1 index - name
     * 2 index - count*/
    private List<Object[]> results;

    private boolean isVoiceChangeDisabled;

    public VoteTo(LocalDate menuDate, List<Object[]> results) {
        this.menuDate = menuDate;
        this.results = results;
        this.isVoiceChangeDisabled = LocalDateTime.of(menuDate, ENDING_CHANGE_TIME).isBefore(LocalDateTime.now());
    }
}
