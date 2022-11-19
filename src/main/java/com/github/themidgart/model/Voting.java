package com.github.themidgart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToMany;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voting extends AbstractEntity {
    @ManyToMany(mappedBy = "user")
    private User user;

    @ManyToMany(mappedBy = "menu")
    private Menu menu;

}
