package com.github.themidgart.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Getter
@Setter
@ToString
public class DishTo extends ToWithId {
    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @NotNull
    @Max(20000)
    @Positive
    private BigDecimal price;

    public DishTo(Integer id, String name, BigDecimal price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
