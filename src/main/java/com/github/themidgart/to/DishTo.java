package com.github.themidgart.to;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class DishTo {
    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @NotNull
    @Max(20000)
    @Positive
    private BigDecimal price;

    @Override
    public String toString() {
        return "Dish {" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
