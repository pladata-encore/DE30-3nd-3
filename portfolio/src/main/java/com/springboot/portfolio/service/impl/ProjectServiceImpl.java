package com.springboot.portfolio.service.impl;

import com.springboot.portfolio.data.dao.ProjectDao;
import com.springboot.portfolio.data.dao.UserDao;
import com.springboot.portfolio.data.dto.ProjectDto;
import com.springboot.portfolio.data.dto.ProjectRequestDto;
import com.springboot.portfolio.data.entity.Project;
import com.springboot.portfolio.data.entity.User;
import com.springboot.portfolio.service.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;
    private final UserDao userDao;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    public ProjectServiceImpl(ProjectDao projectDao,UserDao userDao ){
        this.projectDao = projectDao;
        this.userDao = userDao;
    }

    @Override
    public ProjectDto selectProject(Long prjId) throws Exception{
        Project project = projectDao.selectProject(prjId);
        ProjectDto projectDto
                = new ProjectDto(project.getProjectId(), project.getName(),
                project.getDescription(), project.getStartDate(), project.getEndDate(),
                project.getSkillList(), project.getRole(), project.getImg(), project.getGithub());
            return projectDto;
        }
    @Override
    public ProjectDto createProject(ProjectRequestDto projectRequestDto) throws Exception {
        // Test용 User 생성
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        User user = userDao.findById(userId);

        Project project = new Project();
        project.setName(projectRequestDto.getName());
        project.setStartDate(projectRequestDto.getStart_date());
        project.setEndDate(projectRequestDto.getEnd_date());
        project.setSkillList(projectRequestDto.getSkill_list());
        project.setDescription(projectRequestDto.getDescription());
        project.setRole(projectRequestDto.getRole());
        project.setImg(projectRequestDto.getImg());
        project.setGithub(projectRequestDto.getGithub());
        project.setUser(user);

        Project insertedPrj = projectDao.createProject(project);
        ProjectDto projectDto
                = new ProjectDto(insertedPrj.getProjectId(), insertedPrj.getName(),
                insertedPrj.getDescription(), insertedPrj.getStartDate(), insertedPrj.getEndDate(),
                insertedPrj.getSkillList(), insertedPrj.getRole(), insertedPrj.getImg(), insertedPrj.getGithub());
        return projectDto;
    }

    @Override
    public List<ProjectDto> findByUserId() {
        User newUser = (User) httpSession.getAttribute("logined");
        String userId = newUser.getUser_id();

        List<Project> projects = projectDao.findByUserId(userId);
        return projects.stream()
                .map(project -> new ProjectDto(
                        project.getProjectId(),
                        project.getName(),
                        project.getDescription(),
                        project.getStartDate(),
                        project.getEndDate(),
                        project.getSkillList(),
                        project.getRole(),
                        project.getImg(),
                        project.getGithub()
                )).collect(Collectors.toList());
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) throws Exception {
        Project project = projectDao.selectProject(projectDto.getProject_id());
        if (project == null){

        }
        project.setName(projectDto.getName());
        project.setStartDate(projectDto.getStart_date());
        project.setEndDate(projectDto.getEnd_date());
        project.setSkillList(projectDto.getSkill_list());
        project.setDescription(projectDto.getDescription());
        project.setRole(projectDto.getRole());
        project.setImg(projectDto.getImg());
        project.setGithub(projectDto.getGithub());

        Project updatedProject = projectDao.updateProject(project);
        ProjectDto updatedProjectDto
                = new ProjectDto(updatedProject.getProjectId(), updatedProject.getName(),
                updatedProject.getDescription(), updatedProject.getStartDate(), updatedProject.getEndDate(),
                updatedProject.getSkillList(), updatedProject.getRole(), updatedProject.getImg(), updatedProject.getGithub());
        return updatedProjectDto;
    }

    @Override
    public void deleteById(Long projectId) throws Exception {
        Project project = projectDao.selectProject(projectId);
        if (project == null){
            // throw an exception or handle error
        }

        projectDao.deleteById(projectId);
    }
}
