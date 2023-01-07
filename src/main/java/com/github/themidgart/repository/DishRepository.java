package com.github.themidgart.repository;

import com.github.themidgart.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query("SELECT d FROM dish d WHERE d.id IN :ids")
    List<Dish> findByAnyId(@Param("ids") List<Integer> ids);
}
