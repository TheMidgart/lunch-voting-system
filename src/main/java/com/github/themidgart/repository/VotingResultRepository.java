package com.github.themidgart.repository;

import com.github.themidgart.model.VotingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VotingResultRepository extends JpaRepository<VotingResult, Integer> {
    @Query("SELECT v FROM voting_result v  WHERE v.menu.date=:date AND v.user.id=:userId")
    Optional<VotingResult> getByDateAndUserId(@Param("date") LocalDate date, @Param("userId") int userId);

    @Query("SELECT v FROM voting_result v WHERE v.menu.date =:date")
    Optional<List<VotingResult>> getResultsByDate(@Param("date") LocalDate date);
}
