package com.springboot.portfolio.service.impl;

import com.springboot.portfolio.data.dao.ExperienceDao;
import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.dto.ExperienceDto;
import com.springboot.portfolio.data.dto.ExperienceRequestDto;
import com.springboot.portfolio.data.entity.Experience;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.ExperienceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceDao experienceDao;
    private final UserDao userDao;

    @Autowired
    private HttpSession httpSession;


    @Autowired
    public ExperienceServiceImpl(ExperienceDao experienceDao, UserDao userDao) {
        this.experienceDao = experienceDao;
        this.userDao = userDao;
    }

    @Override
    public ExperienceDto selectExperience(Long expId) throws Exception {
        Experience experience = experienceDao.selectExperience(expId);
        ExperienceDto experienceDto
                = new ExperienceDto(experience.getExp_id(), experience.getCompany_name(),
                experience.getStart_date(), experience.getEnd_date(),experience.getContent(),
                experience.getJob());
        return experienceDto;
    }

    @Override
    public ExperienceDto createExperience(ExperienceRequestDto experienceDto) throws Exception {
        // Test용 User 생성
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        User user = userDao.findById(userId);

        Experience experience = new Experience();
        experience.setCompany_name(experienceDto.getCompanyName());
        experience.setStart_date(String.valueOf(experienceDto.getStartDate()));
        experience.setEnd_date(String.valueOf(experienceDto.getEndDate()));
        experience.setContent(experienceDto.getContent());
        experience.setJob(experienceDto.getJob());
        experience.setUser(user);

        Experience insertedExp = experienceDao.createExperience(experience);

        ExperienceDto experienceResponseDto
                = new ExperienceDto(insertedExp.getExp_id(),
                insertedExp.getCompany_name(), insertedExp.getStart_date(),
                insertedExp.getEnd_date(), insertedExp.getContent(),
                insertedExp.getJob());
        return experienceResponseDto;
    }

    @Override
    public List<ExperienceDto> findByUserId() {
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        List<Experience> experiences = experienceDao.findByUserId(userId);
        return experiences.stream()
                .map(experience -> new ExperienceDto(
                        experience.getExp_id(),
                        experience.getCompany_name(),
                        experience.getStart_date(),
                        experience.getEnd_date(),
                        experience.getContent(),
                        experience.getJob()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceDto updateExperience(ExperienceDto experienceDto) throws Exception {
        Experience experience = experienceDao.selectExperience(experienceDto.getExpId());
        if (experience == null) {
            // throw an exception or handle error
        }

        experience.setCompany_name(experienceDto.getCompanyName());
        experience.setStart_date(experienceDto.getStartDate());
        experience.setEnd_date(experienceDto.getEndDate());
        experience.setContent(experienceDto.getContent());
        experience.setJob(experienceDto.getJob());

        Experience updatedExperience = experienceDao.updateExperience(experience);

        ExperienceDto updatedExperienceDto = new ExperienceDto(
                updatedExperience.getExp_id(),
                updatedExperience.getCompany_name(),
                updatedExperience.getStart_date(),
                updatedExperience.getEnd_date(),
                updatedExperience.getContent(),
                updatedExperience.getJob()
        );
        return updatedExperienceDto;
    }

    @Override
    public void deleteExperience(Long expId) throws Exception {
        Experience experience = experienceDao.selectExperience(expId);
        if (experience == null) {
            // throw an exception or handle error
        }

        experienceDao.deleteExperience(expId);
    }
}
