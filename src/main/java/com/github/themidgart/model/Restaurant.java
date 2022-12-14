package com.github.themidgart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {
    @OneToMany
    @JsonBackReference
    private List <Menu> menuList;
}
