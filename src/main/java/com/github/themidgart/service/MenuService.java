package com.github.themidgart.service;

import com.github.themidgart.exception.NotFoundException;
import com.github.themidgart.model.Dish;
import com.github.themidgart.model.Menu;
import com.github.themidgart.repository.DishRepository;
import com.github.themidgart.repository.MenuRepository;
import com.github.themidgart.repository.RestaurantRepository;
import com.github.themidgart.to.DishesForMenuTo;
import com.github.themidgart.to.MenuTo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;


    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public Menu get(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(MenuTo menuTo) {
        menuRepository.save(createFromTo(menuTo));
    }

    @Transactional
    public Menu update(int id, MenuTo menuTo) {
        if (menuRepository.existsById(id)) {
            return menuRepository.save(updateFromTo(menuRepository.findById(id).get(), menuTo));
        } else {
            throw new NotFoundException("Menu with id " + id + " not found");
        }
    }

    public void delete(int id) {
        menuRepository.deleteById(id);
    }

    @Transactional
    public Menu addDishes(int id, DishesForMenuTo dishesForMenuTo) {
        if (menuRepository.existsById(id)) {
            return menuRepository.save(addDishes(menuRepository.findById(id).get(), dishesForMenuTo));
        } else {
            throw new NotFoundException("Menu with id " + id + " not found");
        }
    }

    public Menu createFromTo(MenuTo menuTo) {
        return new Menu(null, restaurantRepository.findById(menuTo.getRestaurantId()).get(),
                menuTo.getDateMenu(), null);
    }

    public Menu updateFromTo(Menu menu, MenuTo menuTo) {
        menu.setDateMenu(menuTo.getDateMenu());
        menu.setRestaurant(restaurantRepository.findById(menuTo.getRestaurantId()).get());
        return menu;
    }

    public Menu addDishes(Menu menu, DishesForMenuTo dishesForMenuTo) {
        List<Dish> dishes = new ArrayList<>();
        for (Integer currentDishId : dishesForMenuTo.getDishesIds()) {
            dishes.add(dishRepository.findById(currentDishId).get());
        }
        menu.setDishes(dishes);
        return menu;
    }
}
