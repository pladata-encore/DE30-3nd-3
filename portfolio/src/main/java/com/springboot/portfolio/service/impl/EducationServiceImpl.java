package com.springboot.portfolio.service.impl;

import com.springboot.portfolio.data.dao.EducationDao;
import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.dto.EducationDto;
import com.springboot.portfolio.data.dto.EducationRequestDto;
import com.springboot.portfolio.data.dto.ExperienceDto;
import com.springboot.portfolio.data.entity.Education;
import com.springboot.portfolio.data.entity.Experience;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.EducationService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationDao educationDao;
    private final UserDao userDao;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    public EducationServiceImpl(EducationDao educationDao, UserDao userDao) {
        this.educationDao = educationDao;
        this.userDao = userDao;
    }

    @Override
    public EducationDto selectEducation(Long eduId) throws Exception {
        Education education = educationDao.selectEducation(eduId);
        EducationDto educationDto
                = new EducationDto(education.getEdu_id(),
                education.getSchool(),
                education.getGraduate(),
                education.getMajor(),
                education.getStart_date(),
                education.getEnd_date(),
                education.getContent());
        return educationDto;
    }

    @Override
    public EducationDto createEducataion(EducationRequestDto educationRequestDto) throws Exception {

        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        User user = userDao.findById(userId);

        Education education = new Education();
        education.setSchool(educationRequestDto.getSchool());
        education.setGraduate(educationRequestDto.getGraduate());
        education.setMajor(educationRequestDto.getMajor());
        education.setStart_date(educationRequestDto.getStartDate());
        education.setEnd_date(educationRequestDto.getEndDate());
        education.setContent(educationRequestDto.getContent());
        education.setUser(user);

        Education insertedEdu = educationDao.createEducation(education);

        EducationDto educationResponseDto
                = new EducationDto(insertedEdu.getEdu_id(),
                insertedEdu.getSchool(),
                insertedEdu.getGraduate(),
                insertedEdu.getMajor(),
                insertedEdu.getStart_date(),
                insertedEdu.getEnd_date(),
                insertedEdu.getContent());
        return educationResponseDto;
    }

    @Override
    public List<EducationDto> findByUserId() {
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        List<Education> educations = educationDao.findByUserId(userId);
        return educations.stream()
                .map(education -> new EducationDto(
                        education.getEdu_id(),
                        education.getSchool(),
                        education.getGraduate(),
                        education.getMajor(),
                        education.getStart_date(),
                        education.getEnd_date(),
                        education.getContent()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public EducationDto updateEducation(EducationDto educationDto) throws Exception {
        Education education = educationDao.selectEducation(educationDto.getEduId());
        if (education == null) {

        }

        education.setSchool(educationDto.getSchool());
        education.setGraduate(educationDto.getGraduate());
        education.setMajor(educationDto.getMajor());
        education.setStart_date(educationDto.getStart_date());
        education.setEnd_date(educationDto.getEnd_date());
        education.setContent(educationDto.getContent());

        Education updatedEducation = educationDao.updateEducation(education);

        EducationDto updatedEducationDto = new EducationDto(
                updatedEducation.getEdu_id(),
                updatedEducation.getSchool(),
                updatedEducation.getGraduate(),
                updatedEducation.getMajor(),
                updatedEducation.getStart_date(),
                updatedEducation.getEnd_date(),
                updatedEducation.getContent()
        );
        return updatedEducationDto;
    }

    @Override
    public void deleteEducation(Long eduId) throws Exception {
        Education education = educationDao.selectEducation(eduId);
        if (education == null) {

        }
        educationDao.deleteEducation(eduId);
    }
}