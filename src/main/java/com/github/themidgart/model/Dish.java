package com.github.themidgart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @ManyToMany
    private List<Menu> menus;


}
