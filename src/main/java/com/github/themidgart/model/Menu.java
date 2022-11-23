package com.github.themidgart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends AbstractEntity {

    @ManyToOne
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    private LocalDate dateMenu;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    @OrderBy("name")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> dishes;


}
