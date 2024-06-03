package com.springboot.portfolio.data.repository;

import com.springboot.portfolio.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE p.user.user_id= :userId")
    List<Project> findByUserId(@Param("userId") String userId);
}
