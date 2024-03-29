package com.github.themidgart.repository;

import com.github.themidgart.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT v FROM vote v  WHERE v.menu.date=:date AND v.user.id=:userId")
    Optional<Vote> getByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);

    @Query("SELECT v FROM vote v WHERE v.menu.date =:date")
    List<Vote> getResultsByDate(@Param("date") LocalDate date);
}
