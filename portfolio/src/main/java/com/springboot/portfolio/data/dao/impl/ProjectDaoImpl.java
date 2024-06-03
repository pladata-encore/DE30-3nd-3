package com.springboot.portfolio.data.dao.impl;

import com.springboot.portfolio.data.dao.ProjectDao;
import com.springboot.portfolio.data.entity.Project;
import com.springboot.portfolio.data.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectDaoImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    @Override
    public Project selectProject(Long project_id) throws Exception{
        Optional<Project> selectProject = projectRepository.findById(project_id);
        if (selectProject.isPresent()){
            return selectProject.get();
        }
        throw new Exception();
    }
    @Override
    public Project createProject(Project project) {return projectRepository.save(project);}

    @Override
    public List<Project> findByUserId(String userId) {return projectRepository.findByUserId(userId);}

    @Override
    public Project updateProject(Project project) throws Exception {
        Project updateProject = this.selectProject(project.getProjectId());
        if(updateProject != null){
            updateProject.setProjectId(project.getProjectId());
            updateProject.setName(project.getName());
            updateProject.setDescription(project.getDescription());
            updateProject.setStartDate(project.getStartDate());
            updateProject.setEndDate(project.getEndDate());
            updateProject.setSkillList(project.getSkillList());
            updateProject.setRole(project.getRole());
            updateProject.setImg(project.getImg());
            updateProject.setGithub(project.getGithub());
            projectRepository.save(updateProject);
        } else {
            throw new Exception();
        }
        return updateProject;
    }

    @Override
    public void deleteById(Long projectId) throws Exception {
        Project project = selectProject(projectId);
        projectRepository.delete(project);
    }
}
