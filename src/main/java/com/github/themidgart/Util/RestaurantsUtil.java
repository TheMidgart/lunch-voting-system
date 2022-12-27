package com.github.themidgart.Util;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.to.RestaurantTo;

public class RestaurantsUtil {
    public static Restaurant createFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName(), null);
    }

    public static Restaurant updateFromTo(Restaurant restaurant, RestaurantTo restaurantTo){
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }
}
