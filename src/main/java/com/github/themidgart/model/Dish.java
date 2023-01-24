package com.github.themidgart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity (name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    @Max(20000)
    @Positive
    private BigDecimal price;

    public Dish(Integer id, String name, BigDecimal price) {
        super(id, name);
        this.price = price;
    }
}
