package com.github.themidgart.repository;

import com.github.themidgart.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Query("UPDATE restaurant r SET r.name = ?1  WHERE r.id = ?2")
    int updateById(String name, int id);

    @Modifying
    @Query("DELETE FROM restaurant r WHERE r.id =?1")
    int deleteById(int id);
}
