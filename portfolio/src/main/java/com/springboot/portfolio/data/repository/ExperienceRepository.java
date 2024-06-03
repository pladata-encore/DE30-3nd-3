package com.springboot.portfolio.data.repository;

import com.springboot.portfolio.data.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    @Query("SELECT e FROM Experience e WHERE e.user.user_id = :userId")
    List<Experience> findByUserId(@Param("userId") String userId);
}
