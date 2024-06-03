package com.springboot.portfolio.data.dao;

import com.springboot.portfolio.data.entity.Education;

import java.util.List;

public interface EducationDao {
    Education selectEducation(Long eduId) throws Exception;
    Education createEducation(Education education);

    List<Education> findByUserId(String userId);

    Education updateEducation(Education education) throws Exception;

    void deleteEducation(Long eduId) throws Exception;

}
