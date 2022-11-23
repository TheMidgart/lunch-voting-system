package com.github.themidgart.repository;

import com.github.themidgart.model.Voting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingRepository extends JpaRepository<Voting,Integer> {
}
