package com.github.themidgart.repository;

import com.github.themidgart.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Query("SELECT d FROM dish d WHERE d.id IN ?1")
    List<Dish> findByAnyId(List<Integer> ids);

    @Modifying
    @Query("DELETE FROM dish d WHERE d.id =?1")
    int deleteById(int id);

    @Modifying
    @Query("UPDATE dish d SET d.name= ?1, d.price=?2  WHERE d.id = ?3")
    int updateById(String name, BigDecimal price, int id);
}
