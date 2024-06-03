package com.springboot.portfolio.service.impl;

import com.springboot.portfolio.data.dao.SkillDao;
import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.dto.SkillRequestDto;
import com.springboot.portfolio.data.dto.SkillsDto;
import com.springboot.portfolio.data.entity.Skills;
import com.springboot.portfolio.service.SkillService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkilServiceImpl implements SkillService {
    private final SkillDao skillDao;
    private final UserDao userDao;
    @Autowired
    private HttpSession httpSession;

    public SkilServiceImpl(SkillDao skillDao, UserDao userDao) {
        this.skillDao = skillDao;
        this.userDao = userDao;
    }

    @Override
    public SkillsDto createSkill(SkillsDto skillsDto) {
        Skills skills = new Skills();
        skills.setSkill_name(skillsDto.getSkill_name());
        skills.setImage(skillsDto.getImage());
        Skills createdSkill = skillDao.createSkill(skills);

        return new SkillsDto(
                createdSkill.getSkill_name(), createdSkill.getImage()
        );
    }

    @Override
    public SkillsDto selectSkill(String skill) throws Exception {
        Skills skills = skillDao.selectSkill(skill);
        SkillsDto skillsDto
                = new SkillsDto(skills.getSkill_name(), skills.getImage());
        return skillsDto;
    }

    @Override
    public List<SkillsDto> getAllSkills(){
        List<Skills> skills = skillDao.getAllSkills();

        return skills.stream()
                .map(skill -> new SkillsDto(
                        skill.getSkill_name(),
                        skill.getImage()
                )).collect(Collectors.toList());
    }
}
