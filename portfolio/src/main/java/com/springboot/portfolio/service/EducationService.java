package com.springboot.portfolio.service;

import com.springboot.portfolio.data.dto.EducationDto;
import com.springboot.portfolio.data.dto.EducationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EducationService {
    EducationDto selectEducation(Long eduId) throws Exception;
    EducationDto createEducataion(EducationRequestDto educationRequestDto) throws Exception;
    List<EducationDto> findByUserId();
    EducationDto updateEducation(EducationDto educationDto) throws Exception;
    void deleteEducation(Long eduId) throws Exception;
}

