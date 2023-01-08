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

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DishService {
    @Autowired
    private DishRepository repository;

    public List<Dish> getAll() {
        return repository.findAll();
    }

    public Dish get(int id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Dish create(Dish dish) {
        return repository.save(dish);
    }

    @Transactional
    public Dish update(int id, DishTo dishTo) {
        return repository.save(DishesUtil.updateFromTo(Objects.requireNonNull(repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found dish with ID" + id))), dishTo));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
