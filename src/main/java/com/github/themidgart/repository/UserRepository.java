package com.github.themidgart.repository;

import com.github.themidgart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query("DELETE FROM users u WHERE u.id = ?1")
    int deleteById(int id);

    Optional<User> findByEmailIgnoreCase(String email);
}
