package com.github.themidgart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    private BigDecimal price;


}
