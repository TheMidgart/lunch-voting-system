package com.github.themidgart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "menu")
public class Menu extends AbstractEntity {

    @ManyToOne
    @JsonManagedReference
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate dateMenu;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "dish_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    @OrderBy("name")
    private List<Dish> dishes;

    @OneToMany(mappedBy = "menu")
    @JsonBackReference
    @Hidden
    Set<VotingResult> votingResults;

    public Menu(Integer id, Restaurant restaurant, LocalDate dateMenu, List<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.dateMenu = dateMenu;
        this.dishes = dishes;
    }
}
