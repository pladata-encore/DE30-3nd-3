package com.springboot.portfolio.data.repository;

import com.springboot.portfolio.data.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    @Query("SELECT e FROM Education e WHERE e.user.user_id = :userId")
    List<Education> findByUserId(@Param("userId") String userId);

}
