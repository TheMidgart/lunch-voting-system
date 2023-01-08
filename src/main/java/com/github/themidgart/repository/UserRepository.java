package com.github.themidgart.repository;

import com.github.themidgart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Query("DELETE FROM users u WHERE u.id = :id")
    void deleteById(@Param("id") int id);

    Optional<User> findByEmailIgnoreCase(String email);
}
