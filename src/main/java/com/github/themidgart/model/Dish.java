package com.github.themidgart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity (name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    @Max(20000)
    @Positive
    private BigDecimal price;

    @ManyToMany(mappedBy = "dishes")
    @JsonBackReference
    private List<Menu> menuList;

    public Dish(Integer id, String name, BigDecimal price, List<Menu> menuList) {
        super(id, name);
        this.price = price;
        this.menuList = menuList;
    }
}
