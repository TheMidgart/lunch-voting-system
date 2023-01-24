package com.github.themidgart.repository;

import com.github.themidgart.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT m FROM menu m WHERE m.dateMenu = :date")
    Optional<List<Menu>> getAllByDate(@Param("date") LocalDate date);

    @Query("SELECT m FROM menu m WHERE m.restaurant.id=:restaurantId AND m.dateMenu=:date")
    Optional<Menu> getMenuByRestaurantIdAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Modifying
    @Query("DELETE FROM menu m WHERE m.id = :id")

    void deleteById(@Param("id") int id);
}
