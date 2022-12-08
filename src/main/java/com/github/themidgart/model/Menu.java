package com.github.themidgart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "menu")
public class Menu extends AbstractEntity {

    @ManyToOne
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate dateMenu;

    @ManyToMany
    @JoinTable(name = "dish_menu",
    joinColumns = @JoinColumn(name = "menu_id"),
    inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @OrderBy("name")
    private List<Dish> dishes;


}
