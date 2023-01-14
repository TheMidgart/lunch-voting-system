package com.github.themidgart.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RestaurantTo extends ToWithId {
    @NotNull
    @Size(min = 2, max =255)
    private String name;

    public RestaurantTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant {" +
                "name='" + name + '\'' +
                '}';
    }

}
