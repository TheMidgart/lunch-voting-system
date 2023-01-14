package com.github.themidgart.util;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.to.RestaurantTo;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RestaurantsUtil {
    public static Restaurant createFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName(), null);
    }

    public static Restaurant updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo){
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }

    public static RestaurantTo createToFromModel(Restaurant restaurant){
        return new RestaurantTo(restaurant.getId(), restaurant.getName());
    }
}
