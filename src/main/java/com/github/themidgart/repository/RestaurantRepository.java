package com.github.themidgart.repository;

import com.github.themidgart.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Modifying
    @Query("DELETE FROM restaurant r WHERE r.id = :id")
    void deleteById(@Param("id") int id);
}
