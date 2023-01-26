package com.github.themidgart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "menu")
public class Menu extends AbstractEntity {

    @ManyToOne
    @JsonManagedReference
    @NotNull
    private Restaurant restaurant;

    @Column(name = "menu_date", nullable = false)
    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    @JoinTable(name = "dish_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @OrderBy("name")
    private List<Dish> dishes;

    @OneToMany(mappedBy = "menu")
    @JsonBackReference
    @JsonIgnore
    @ApiParam(hidden = true)
    Set<Vote> votes;

    public Menu(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dishes = dishes;
    }
}
