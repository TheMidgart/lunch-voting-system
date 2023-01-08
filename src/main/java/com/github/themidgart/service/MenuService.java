package com.github.themidgart.service;

import com.github.themidgart.model.Dish;
import com.github.themidgart.model.Menu;
import com.github.themidgart.repository.DishRepository;
import com.github.themidgart.repository.MenuRepository;
import com.github.themidgart.repository.RestaurantRepository;
import com.github.themidgart.to.DishesForMenuTo;
import com.github.themidgart.to.MenuTo;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Cacheable("menus")
    public List<Menu> getAllByDate(LocalDate date) {
        return menuRepository.getAllByDate(date).orElseThrow(() -> new NotFoundException("Menus not found on date:" + date));
    }

    public Menu get(int id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public void save(MenuTo menuTo) {
        menuRepository.save(createFromTo(menuTo));
    }

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public Menu update(int id, MenuTo menuTo) {
        if (menuRepository.existsById(id)) {
            return menuRepository.save(updateFromTo(menuRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Not found menu with ID" + id)), menuTo));
        } else {
            throw new NotFoundException("Menu with id " + id + " not found");
        }
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(int id) {
        menuRepository.deleteById(id);
    }

    @Transactional
    public Menu addDishes(int id, DishesForMenuTo dishesForMenuTo) {
        if (menuRepository.existsById(id)) {
            return menuRepository.save(addDishesFromTo(menuRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Not found menu with ID" + id)), dishesForMenuTo));
        } else {
            throw new NotFoundException("Menu with id " + id + " not found");
        }
    }

    public Menu createFromTo(MenuTo menuTo) {
        return new Menu(null, restaurantRepository.findById(menuTo.getRestaurantId())
                .orElseThrow(() -> new NotFoundException("Not found restaurant with ID" + menuTo.getRestaurantId())),
                menuTo.getDateMenu(), null);
    }

    public Menu updateFromTo(Menu menu, MenuTo menuTo) {
        menu.setDateMenu(menuTo.getDateMenu());
        menu.setRestaurant(restaurantRepository.findById(menuTo.getRestaurantId())
                .orElseThrow(() -> new NotFoundException("Not found restaurant with ID" + menuTo.getRestaurantId())));
        return menu;
    }

    public Menu addDishesFromTo(Menu menu, DishesForMenuTo dishesForMenuTo) {
        List<Dish> dishes = dishRepository.findByAnyId(dishesForMenuTo.getDishesIds());
        menu.setDishes(dishes);
        return menu;
    }
}
