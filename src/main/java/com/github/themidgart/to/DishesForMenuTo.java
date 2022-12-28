package com.github.themidgart.to;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DishesForMenuTo {
    @NotNull
    private List<Integer> dishesIds;

    @Override
    public String toString() {
        return "dishesIds=" + dishesIds;
    }
}
