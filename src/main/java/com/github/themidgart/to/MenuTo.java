package com.github.themidgart.to;

import lombok.Data;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MenuTo {

    @NotNull
    private Integer restaurantId;
    @NotNull
    @FutureOrPresent
    private LocalDate dateMenu;
}
