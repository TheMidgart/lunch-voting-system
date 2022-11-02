package com.github.themidgart.model;

import java.math.BigDecimal;

public class Dish extends AbstractNamedEntity{

    private BigDecimal price;

    public Dish() {
    }

    public Dish(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
