package com.springboot.portfolio.service;

import com.springboot.portfolio.data.dto.ExperienceDto;
import com.springboot.portfolio.data.dto.ExperienceRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExperienceService {
    ExperienceDto selectExperience(Long expId) throws Exception;
    ExperienceDto createExperience(ExperienceRequestDto experienceRequestDto) throws Exception;
    List<ExperienceDto> findByUserId();
    ExperienceDto updateExperience(ExperienceDto experienceDto) throws Exception;
    void deleteExperience(Long expId) throws Exception;
}
