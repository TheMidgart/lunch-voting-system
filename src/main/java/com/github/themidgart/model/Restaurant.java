package com.github.themidgart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {
    @OneToMany
    @JsonBackReference
    private List<Menu> menuList;

    public Restaurant(Integer id, String name, List<Menu> menuList) {
        super(id, name);
        this.menuList = menuList;
    }
}
