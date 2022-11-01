package com.github.themidgart.model;

import java.time.LocalDate;
import java.util.List;

public class Menu extends AbstractEntity {

    private String restaurantName;

    private LocalDate dateMenu;

    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(String restaurantName, LocalDate dateMenu, List<Dish> dishes) {
        this.restaurantName = restaurantName;
        this.dateMenu = dateMenu;
        this.dishes = dishes;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDate getDateMenu() {
        return dateMenu;
    }

    public void setDateMenu(LocalDate dateMenu) {
        this.dateMenu = dateMenu;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
