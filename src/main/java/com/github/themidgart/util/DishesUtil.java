package com.github.themidgart.util;

import com.github.themidgart.model.Dish;
import com.github.themidgart.to.DishTo;

public class DishesUtil {
    public static Dish createFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getName(), dishTo.getPrice(),null);
    }

    public static Dish updateFromTo(Dish dish, DishTo dishTo){
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        return dish;
    }
}
