package com.github.themidgart.service;

import com.github.themidgart.model.Dish;
import com.github.themidgart.repository.DishRepository;
import com.github.themidgart.to.DishTo;
import com.github.themidgart.util.DishesUtil;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.themidgart.util.exception.ExceptionMessages.DISH_NOT_FOUND_WITH_ID;

@Service
@RequiredArgsConstructor
public class DishService {
    private final DishRepository repository;

    public List<DishTo> getAll() {
        return repository.findAll().stream().map(DishesUtil::createToFromModel).toList();
    }

    public DishTo get(int id) {
        return DishesUtil.createToFromModel(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(DISH_NOT_FOUND_WITH_ID + id)));
    }

    @Transactional
    public Dish create(Dish dish) {
        return repository.save(dish);
    }
}
