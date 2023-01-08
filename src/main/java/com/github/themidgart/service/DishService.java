package com.github.themidgart.service;

import com.github.themidgart.model.Dish;
import com.github.themidgart.repository.DishRepository;
import com.github.themidgart.to.DishTo;
import com.github.themidgart.util.DishesUtil;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.github.themidgart.util.exception.ExceptionMessages.DISH_NOT_FOUND_WITH_ID;

@Service
@AllArgsConstructor
public class DishService {
    @Autowired
    private DishRepository repository;

    public List<Dish> getAll() {
        return repository.findAll();
    }

    public Dish get(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(DISH_NOT_FOUND_WITH_ID + id));
    }

    @Transactional
    public Dish create(Dish dish) {
        return repository.save(dish);
    }

    @Transactional
    public Dish update(int id, DishTo dishTo) {
        return repository.save(DishesUtil.updateFromTo(Objects.requireNonNull(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(DISH_NOT_FOUND_WITH_ID + id))), dishTo));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
