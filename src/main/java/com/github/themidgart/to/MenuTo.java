package com.github.themidgart.to;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MenuTo {
    @NotNull
    private String name;

    @NotNull
    @Min(100000)
    private Integer restaurantId;

    @NotNull
    private LocalDate dateMenu;
}
