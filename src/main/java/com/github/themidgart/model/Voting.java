package com.github.themidgart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Voting extends AbstractEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Menu menu;

}
