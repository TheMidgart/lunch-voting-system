package com.github.themidgart.to;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RestaurantTo {
    @NotNull
    @Size(min = 2, max =255)
    private String name;
}
