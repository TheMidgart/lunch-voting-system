package com.github.themidgart.repository;

import com.github.themidgart.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
