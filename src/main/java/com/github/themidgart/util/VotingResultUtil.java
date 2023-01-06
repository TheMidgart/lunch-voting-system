package com.github.themidgart.util;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.model.VotingResult;
import com.github.themidgart.to.VotingResultTo;

import java.util.List;
import java.util.stream.Collectors;

public class VotingResultUtil {
    public static VotingResultTo toSummaryResults(List<VotingResult> results){
        return new VotingResultTo(results.get(0).getMenu().getDateMenu(), results.stream()
                .map(res->res.getMenu().getRestaurant())
                .collect(Collectors.groupingBy(Restaurant::getName,Collectors.counting())));
    }
}
