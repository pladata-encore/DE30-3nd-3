package com.springboot.portfolio.data.repository;

import com.springboot.portfolio.data.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skills, String> {
    @Query("SELECT s FROM Skills s")
    List<Skills> getAllSkills();
}
