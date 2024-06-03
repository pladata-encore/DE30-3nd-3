package com.springboot.portfolio.data.dao.impl;

import com.springboot.portfolio.data.dao.SkillDao;
import com.springboot.portfolio.data.entity.Skills;
import com.springboot.portfolio.data.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SkillDaoImpl implements SkillDao {
    private final SkillRepository skillRepository;

    @Override
    public Skills createSkill(Skills skills) {
        return skillRepository.save(skills);
    }

    @Override
    public Skills selectSkill(String skillName) throws Exception {
        Optional<Skills> optionalSkill = skillRepository.findById(skillName);
        if(optionalSkill.isPresent()){
            return optionalSkill.get();
        }
        throw new Exception();
    }

    @Override
    public List<Skills> getAllSkills(){return skillRepository.getAllSkills();}
}
