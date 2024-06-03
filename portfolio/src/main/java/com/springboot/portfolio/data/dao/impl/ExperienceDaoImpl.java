package com.springboot.portfolio.data.dao.impl;

import com.springboot.portfolio.data.dao.ExperienceDao;
import com.springboot.portfolio.data.entity.Experience;
import com.springboot.portfolio.data.repository.ExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ExperienceDaoImpl implements ExperienceDao {
    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceDaoImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public Experience selectExperience(Long expId) throws Exception {
        Optional<Experience> selectExperience = experienceRepository.findById(expId);
        if (selectExperience.isPresent()){
            return selectExperience.get();
        }
        throw new Exception();
    }

    @Override
    public Experience createExperience(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public List<Experience> findByUserId(String userId) {
        return experienceRepository.findByUserId(userId);
    }

    @Override
    public Experience updateExperience(Experience experience) throws Exception {
        Experience updateExperience = this.selectExperience(experience.getExp_id());
        if(updateExperience != null) {
            updateExperience.setCompany_name(experience.getCompany_name());
            updateExperience.setJob(experience.getJob());
            updateExperience.setContent(experience.getContent());
            updateExperience.setStart_date(experience.getStart_date());
            updateExperience.setEnd_date(experience.getEnd_date());
            experienceRepository.save(updateExperience);
        } else {
            throw new Exception();
        }
        return updateExperience;
    }

    @Override
    public void deleteExperience(Long expId) throws Exception {
        Experience experience = this.selectExperience(expId);
        experienceRepository.delete(experience);
    }
}
