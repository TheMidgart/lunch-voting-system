package com.github.themidgart.repository;

import com.github.themidgart.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query("SELECT m FROM menu m WHERE m.dateMenu = :date")
    Optional<List<Menu>> getAllByDate(@Param("date") LocalDate date);
}
