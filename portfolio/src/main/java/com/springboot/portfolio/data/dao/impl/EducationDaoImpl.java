package com.springboot.portfolio.data.dao.impl;

import com.springboot.portfolio.data.dao.EducationDao;
import com.springboot.portfolio.data.entity.Education;
import com.springboot.portfolio.data.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public class EducationDaoImpl implements EducationDao {
    private final EducationRepository educationRepository;

    @Autowired
    public EducationDaoImpl(EducationRepository educationRepository){
        this.educationRepository = educationRepository;
    }

    @Override
    public Education selectEducation(Long eduId) throws Exception {
        Optional<Education> selectEducation = educationRepository.findById(eduId);
        if (selectEducation.isPresent()) {
            return selectEducation.get();
        }
        throw new Exception();
    }

    @Override
    public Education createEducation(Education education) {
        return educationRepository.save(education);
    }

    @Override
    public List<Education> findByUserId(String userId) {
        return educationRepository.findByUserId(userId);
    }

    @Override
    public Education updateEducation(Education education) throws Exception {
        Education updateEducation = this.selectEducation(education.getEdu_id());
        if (updateEducation != null) {
            updateEducation.setSchool(education.getSchool());
            updateEducation.setGraduate(education.getGraduate());
            updateEducation.setMajor(education.getMajor());
            updateEducation.setStart_date(education.getStart_date());
            updateEducation.setEnd_date(education.getEnd_date());
            educationRepository.save(updateEducation);
        } else {
            throw new Exception();
        }
        return updateEducation;
    }


    @Override
    public void deleteEducation(Long eduId) throws Exception {
        Education education = this.selectEducation(eduId);
        educationRepository.delete(education);
    }
}

