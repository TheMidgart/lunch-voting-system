package com.github.themidgart.util;

import com.github.themidgart.model.Dish;
import com.github.themidgart.to.DishTo;
import com.github.themidgart.to.DishesForMenuTo;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DishesUtil {
    public static Dish createFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getName(), dishTo.getPrice());
    }

    public static DishTo createToFromModel(Dish dish) {
        return new DishTo(dish.getId(), dish.getName(), dish.getPrice());
    }

    public static List<Dish> checkIncorrectDishesIds(List<Dish> dishes, DishesForMenuTo to) {
        if (dishes.size() == to.getDishesIds().size()) {
            return dishes;
        } else
            throw new IllegalArgumentException("ids are incorrect");
    }

}
