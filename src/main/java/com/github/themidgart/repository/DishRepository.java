package com.github.themidgart.repository;

import com.github.themidgart.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}
