package com.springboot.portfolio.service;

import com.springboot.portfolio.data.dto.SkillsDto;

import java.util.List;

public interface SkillService {
    SkillsDto createSkill(SkillsDto skillsDto);

    SkillsDto selectSkill(String skill) throws Exception;
    List<SkillsDto> getAllSkills() throws Exception;
}
