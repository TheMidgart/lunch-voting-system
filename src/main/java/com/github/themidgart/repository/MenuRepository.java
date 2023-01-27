package com.github.themidgart.repository;

import com.github.themidgart.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT m FROM menu m WHERE m.date =?1")
    List<Menu> getAllByDate(LocalDate date);

    @Query("SELECT m FROM menu m WHERE m.restaurant.id=?1 AND m.date=?2")
    Optional<Menu> getMenuByRestaurantIdAndDate(int restaurantId, LocalDate date);

    @Modifying
    @Query("UPDATE menu m SET m.date= ?1, m.restaurant.id=?2  WHERE m.id = ?3")
    int updateById(LocalDate date, int restaurantId, int id);

    @Modifying
    @Query("DELETE FROM menu m WHERE m.id =?1")
    int deleteById(int id);
}
