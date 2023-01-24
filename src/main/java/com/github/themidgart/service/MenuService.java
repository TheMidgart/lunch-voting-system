package com.github.themidgart.service;

import com.github.themidgart.model.Dish;
import com.github.themidgart.model.Menu;
import com.github.themidgart.repository.DishRepository;
import com.github.themidgart.repository.MenuRepository;
import com.github.themidgart.repository.RestaurantRepository;
import com.github.themidgart.to.DishesForMenuTo;
import com.github.themidgart.to.MenuTo;
import com.github.themidgart.util.DishesUtil;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.github.themidgart.util.ValidationUtil.checkNotFound;
import static com.github.themidgart.util.exception.ExceptionMessages.MENUS_NOT_FOUND_ON_DATE;
import static com.github.themidgart.util.exception.ExceptionMessages.MENU_NOT_FOUND_WITH_ID;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Cacheable("menus")
    public List<Menu> getAllByDate(LocalDate date) {
        return menuRepository.getAllByDate(date).orElseThrow(() -> new NotFoundException(MENUS_NOT_FOUND_ON_DATE + date));
    }

    public Menu get(int id) {
        return menuRepository.findById(id).orElseThrow(() -> new NotFoundException(MENU_NOT_FOUND_WITH_ID + id));
    }

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public void save(MenuTo menuTo) {
        menuRepository.save(createFromTo(menuTo));
    }

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public void update(int id, MenuTo menuTo) {
        checkNotFound(menuRepository.updateById(menuTo.getDateMenu(),menuTo.getRestaurantId(),id));
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(int id) {
        checkNotFound(menuRepository.deleteById(id));
    }

    @Transactional
    public Menu addDishes(int id, DishesForMenuTo dishesForMenuTo) {
        return menuRepository.save(addDishesFromTo(menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MENU_NOT_FOUND_WITH_ID + id)), dishesForMenuTo));
    }

    public Menu createFromTo(MenuTo menuTo) {
        return new Menu(null, restaurantRepository.getReferenceById(menuTo.getRestaurantId()),
                menuTo.getDateMenu(), null);
    }

    public Menu addDishesFromTo(Menu menu, DishesForMenuTo dishesForMenuTo) {
        List<Dish> dishes = dishRepository.findByAnyId(dishesForMenuTo.getDishesIds());
        menu.setDishes(DishesUtil.checkIncorrectDishesIds(dishes,dishesForMenuTo));
        return menu;
    }
}
