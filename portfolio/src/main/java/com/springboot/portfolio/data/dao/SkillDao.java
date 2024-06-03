package com.springboot.portfolio.data.dao;

import com.springboot.portfolio.data.entity.Skills;

import java.util.List;
import java.util.Optional;

public interface SkillDao {
    Skills createSkill(Skills skills);

    Skills selectSkill(String skillName) throws Exception;
    List<Skills> getAllSkills();
}
