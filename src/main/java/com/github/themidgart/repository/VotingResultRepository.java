package com.github.themidgart.repository;

import com.github.themidgart.model.VotingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingResultRepository extends JpaRepository<VotingResult,Integer> {
}
