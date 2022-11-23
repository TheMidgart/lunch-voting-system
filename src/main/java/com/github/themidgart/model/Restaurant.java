package com.github.themidgart.model;

import javax.persistence.OneToMany;
import java.util.List;

public class Restaurant extends AbstractNamedEntity {
    @OneToMany
    List <Menu> menuList;
}
