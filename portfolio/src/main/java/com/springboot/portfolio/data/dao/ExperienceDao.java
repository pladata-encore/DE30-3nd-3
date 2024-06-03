package com.springboot.portfolio.data.dao;

import com.springboot.portfolio.data.entity.Experience;

import java.util.List;
import java.util.Optional;

public interface ExperienceDao {
    Experience selectExperience(Long expId) throws Exception;
    Experience createExperience(Experience experience);

    List<Experience> findByUserId(String userId);

    Experience updateExperience(Experience experience) throws Exception;

    void deleteExperience(Long expId) throws Exception;
}
